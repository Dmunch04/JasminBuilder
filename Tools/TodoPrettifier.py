with open ('../TODO.md', 'r+') as File:
	Lines = File.readlines ()
	File.seek (0)
	File.truncate ()
	FixedLines = []

	for Line in Lines:
		Path, Number, _, Message = Line.split (':')
		Path = Path.replace ('../JasminBuilder/', '')
		Number = Number.strip ()
		Message = Message.strip ()
		FixedLines.append ('- ' + '`' + Path  + ':' + Number + '`: ' + Message)

	File.write ('\n'.join (FixedLines))
