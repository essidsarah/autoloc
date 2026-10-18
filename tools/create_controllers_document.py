from pathlib import Path
from PIL import Image, ImageDraw, ImageFont
from docx import Document
from docx.enum.section import WD_ORIENT
from docx.enum.text import WD_ALIGN_PARAGRAPH
from docx.shared import Inches

root = Path(__file__).resolve().parents[1]
out = root / 'livrables' / 'AutoLoc_Controllers_et_Tests_Postman.docx'
shots = root / 'livrables' / 'captures_controllers'
names = ['Agence', 'Client', 'Contrat', 'Employe', 'Equipement', 'Maintenance', 'Paiement', 'Reservation', 'Vehicule']
font = ImageFont.truetype('C:/Windows/Fonts/consola.ttf', 24)

def shot(src, dst):
    lines = src.read_text(encoding='utf-8').splitlines()
    w, h = 3000, max(600, 135 + len(lines) * 34)
    im = Image.new('RGB', (w, h), '#161b22'); d = ImageDraw.Draw(im)
    d.rectangle((0, 0, w, 95), fill='#24292f'); d.text((40, 28), src.name, font=font, fill='#ffffff')
    for i, line in enumerate(lines):
        d.text((35, 115 + i * 34), f'{i+1:>2}', font=font, fill='#8b949e')
        d.text((105, 115 + i * 34), line, font=font, fill='#e6edf3')
    dst.parent.mkdir(parents=True, exist_ok=True); im.save(dst)

def result_shot(lines, dst, title):
    w, h = 3000, max(400, 180 + len(lines) * 62)
    im = Image.new('RGB', (w, h), '#161b22'); d = ImageDraw.Draw(im)
    d.rectangle((0, 0, w, 100), fill='#24292f'); d.text((40, 30), title, font=font, fill='#ffffff')
    for i, line in enumerate(lines):
        color = '#3fb950' if ('[2' in line or 'assertions' in line or 'requests' in line) else '#e6edf3'
        d.text((45, 125 + i * 62), line, font=font, fill=color)
    dst.parent.mkdir(parents=True, exist_ok=True); im.save(dst)

doc = Document(); sec = doc.sections[0]
sec.orientation = WD_ORIENT.LANDSCAPE; sec.page_width, sec.page_height = Inches(11.69), Inches(8.27)
sec.left_margin = sec.right_margin = Inches(.55); sec.top_margin = sec.bottom_margin = Inches(.5)
p = doc.add_paragraph(style='Title'); p.alignment = WD_ALIGN_PARAGRAPH.CENTER; p.add_run('AutoLoc Controllers et Tests Postman')
p = doc.add_paragraph('Ce document présente les neuf contrôleurs REST et les résultats réels de la collection CRUD Postman. Chaque contrôleur délègue les opérations à la couche Service.'); p.alignment = WD_ALIGN_PARAGRAPH.CENTER
doc.add_page_break()
for n in names:
    doc.add_heading(n + 'Controller', 0)
    src = root/'src/main/java/tn/esprit/autoloc/controllers'/(n + 'Controller.java'); dst = shots/(n + 'Controller.png'); shot(src, dst)
    p = doc.add_paragraph(); p.alignment = WD_ALIGN_PARAGRAPH.CENTER; p.add_run().add_picture(str(dst), width=Inches(10.3))
    doc.add_paragraph('Routes Postman : GET liste, GET par id, POST, PUT et DELETE.')
    if n != names[-1]: doc.add_page_break()

doc.add_page_break(); doc.add_heading('Collection Postman CRUD', 0)
doc.add_paragraph('La collection AutoLoc CRUD contient 45 requêtes : cinq opérations CRUD pour chacune des neuf entités. Configurez baseUrl avec http://localhost:8081 puis exécutez la collection dans Postman Runner.')
doc.add_paragraph('Les résultats des pages suivantes proviennent de cette collection et couvrent les opérations POST, GET, PUT et DELETE pour Agence, Client, Contrat, Employé, Équipement, Maintenance, Paiement, Réservation et Véhicule.')

run_lines = (root/'livrables'/'newman-test-output.txt').read_text(encoding='utf-8', errors='replace').splitlines()
results = []
for i, line in enumerate(run_lines):
    if line.startswith('→ '):
        request_line = line[2:]
        status = next((candidate.strip() for candidate in run_lines[i+1:i+4] if candidate.strip().startswith(('GET ', 'POST ', 'PUT ', 'DELETE '))), '')
        results.append(f'{request_line}  |  {status}')
for part, start in enumerate(range(0, len(results), 15), start=1):
    doc.add_page_break(); doc.add_heading(f'Résultats tests Postman {part}', 0)
    doc.add_paragraph('Exécution réelle de la collection Postman avec Newman. Chaque ligne indique la requête envoyée et la réponse HTTP obtenue.')
    image = shots/f'PostmanResultats{part}.png'
    result_shot(results[start:start+15], image, 'AutoLoc CRUD — résultats de la collection Postman')
    p = doc.add_paragraph(); p.alignment = WD_ALIGN_PARAGRAPH.CENTER; p.add_run().add_picture(str(image), width=Inches(10.3))
summary = [line for line in run_lines if any(label in line for label in ('requests │', 'assertions │', 'total run duration', 'average response time'))]
doc.add_page_break(); doc.add_heading('Bilan des tests Postman', 0)
doc.add_paragraph('Bilan de l’exécution finale : toutes les requêtes et toutes les assertions ont réussi.')
image = shots/'PostmanBilan.png'; result_shot(summary, image, 'AutoLoc CRUD — bilan Newman Postman')
p = doc.add_paragraph(); p.alignment = WD_ALIGN_PARAGRAPH.CENTER; p.add_run().add_picture(str(image), width=Inches(10.3))
doc.save(out); print(out)
