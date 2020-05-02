import datetime

with open ('../TODO.md', 'r+') as File:
	Lines = File.readlines ()
	File.seek (0)
	File.truncate ()
	Elements = []

	for Line in Lines:
		Path, Number, _, Message = Line.split (':')
		Path = Path.replace ('../JasminBuilder/', '')
		Number = Number.strip ()
		FilePath = f'{Path}:{Number}'
		Status = 'N/A'
		
		if '->' in Message:
			Message, Status = Message.split ('->')
			Message = Message.strip ()
			Status = Status.strip ()

		else:
			Message = Message.strip ()

		Elements.append (f'| {Message} | `{Status}` | `{FilePath}` |')

	File.write ('# TODO\n\n')
	File.write ('| Message | Status | File |\n')
	File.write ('| --- | --- | --- |\n')
	File.write ('\n'.join (Elements))
	File.write (f'\n\n> Last generated at: `{datetime.datetime.now ().strftime ("%d/%m/%y - %H:%M:%S")}`')
