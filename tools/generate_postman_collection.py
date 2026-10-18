import json
from pathlib import Path


OUT = Path(__file__).resolve().parents[1] / "postman" / "AutoLoc CRUD.postman_collection.json"
BASE = "{{baseUrl}}"

ENTITIES = [
    ("Agence", "agences", "idAgence", {"nom": "AutoLoc Tunis", "ville": "Tunis", "adresse": "Centre Ville", "telephone": "70111222"}, {"idAgence": "{{idAgence}}", "nom": "AutoLoc La Marsa", "ville": "La Marsa", "adresse": "Avenue Habib Bourguiba", "telephone": "70111223"}),
    ("Client", "clients", "idClient", {"nom": "Ben Ali", "prenom": "Sami", "email": "sami.benali@autoloc.tn", "telephone": "22111222", "numPermis": "TN123456", "dateInscription": "2026-10-18"}, {"idClient": "{{idClient}}", "nom": "Ben Ali", "prenom": "Sami", "email": "sami.benali@autoloc.tn", "telephone": "22111223", "numPermis": "TN123456", "dateInscription": "2026-10-18"}),
    ("Equipement", "equipements", "idEquipement", {"libelle": "GPS"}, {"idEquipement": "{{idEquipement}}", "libelle": "GPS connecté"}),
    ("Vehicule", "vehicules", "idVehicule", {"immatriculation": "123TU4567", "marque": "Toyota", "modele": "Yaris", "categorie": "CITADINE", "tarifJournalier": 85, "statut": "DISPONIBLE", "agence": {"idAgence": "{{idAgence}}"}}, {"idVehicule": "{{idVehicule}}", "immatriculation": "123TU4567", "marque": "Toyota", "modele": "Yaris Hybride", "categorie": "CITADINE", "tarifJournalier": 90, "statut": "DISPONIBLE", "agence": {"idAgence": "{{idAgence}}"}}),
    ("Employe", "employes", "idEmploye", {"nom": "Mansour", "prenom": "Aya", "role": "AGENT", "agence": {"idAgence": "{{idAgence}}"}}, {"idEmploye": "{{idEmploye}}", "nom": "Mansour", "prenom": "Aya", "role": "MANAGER", "agence": {"idAgence": "{{idAgence}}"}}),
    ("Maintenance", "maintenances", "idMaintenance", {"dateDebut": "2026-10-20", "dateFin": "2026-10-21", "description": "Révision périodique", "vehicule": {"idVehicule": "{{idVehicule}}"}}, {"idMaintenance": "{{idMaintenance}}", "dateDebut": "2026-10-20", "dateFin": "2026-10-22", "description": "Révision complète", "vehicule": {"idVehicule": "{{idVehicule}}"}}),
    ("Reservation", "reservations", "idReservation", {"dateDebut": "2026-11-01", "dateFin": "2026-11-05", "statut": "EN_ATTENTE", "client": {"idClient": "{{idClient}}"}, "vehicule": {"idVehicule": "{{idVehicule}}"}}, {"idReservation": "{{idReservation}}", "dateDebut": "2026-11-01", "dateFin": "2026-11-06", "statut": "CONFIRMEE", "client": {"idClient": "{{idClient}}"}, "vehicule": {"idVehicule": "{{idVehicule}}"}}),
    ("Contrat", "contrats", "idContrat", {"dateSignature": "2026-10-18", "montantTotal": 450, "valide": True, "reservation": {"idReservation": "{{idReservation}}"}}, {"idContrat": "{{idContrat}}", "dateSignature": "2026-10-18", "montantTotal": 480, "valide": True, "reservation": {"idReservation": "{{idReservation}}"}}),
    ("Paiement", "paiements", "idPaiement", {"montant": 480, "datePaiement": "2026-10-18", "modePaiement": "CARTE", "contrat": {"idContrat": "{{idContrat}}", "valide": True}}, {"idPaiement": "{{idPaiement}}", "montant": 480, "datePaiement": "2026-10-18", "modePaiement": "VIREMENT", "contrat": {"idContrat": "{{idContrat}}", "valide": True}}),
]


def request(name, method, url, body=None, expected=200, save_id=None):
    data = {"name": name, "request": {"method": method, "header": [], "url": url}}
    if body is not None:
        data["request"]["header"] = [{"key": "Content-Type", "value": "application/json"}]
        data["request"]["body"] = {"mode": "raw", "raw": json.dumps(body, ensure_ascii=False, indent=2), "options": {"raw": {"language": "json"}}}
    script = [f"pm.test('HTTP {expected}', function () {{ pm.response.to.have.status({expected}); }});"]
    if save_id:
        script.append(f"pm.collectionVariables.set('{save_id}', pm.response.json().{save_id});")
    data["event"] = [{"listen": "test", "script": {"type": "text/javascript", "exec": script}}]
    return data


items = []
for entity, route, identifier, create, update in ENTITIES:
    items.append(request(f"{entity} - POST", "POST", f"{BASE}/api/{route}", create, 201, identifier))
for entity, route, identifier, create, update in ENTITIES:
    items.append(request(f"{entity} - GET tous", "GET", f"{BASE}/api/{route}"))
    items.append(request(f"{entity} - GET un", "GET", f"{BASE}/api/{route}/{{{{{identifier}}}}}"))
    items.append(request(f"{entity} - PUT", "PUT", f"{BASE}/api/{route}", update))
for entity, route, identifier, create, update in reversed(ENTITIES):
    items.append(request(f"{entity} - DELETE", "DELETE", f"{BASE}/api/{route}/{{{{{identifier}}}}}", expected=204))

collection = {
    "info": {"name": "AutoLoc CRUD", "schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json"},
    "variable": [{"key": "baseUrl", "value": "http://localhost:8081"}],
    "item": items,
}
OUT.parent.mkdir(parents=True, exist_ok=True)
OUT.write_text(json.dumps(collection, ensure_ascii=False, indent=2), encoding="utf-8")
print(OUT)
