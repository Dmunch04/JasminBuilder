egrep -nr '(TODO)' ../JasminBuilder/src/ | sed 's/: */: /g' > ../TODO.md
python3 TodoPrettifier.py
