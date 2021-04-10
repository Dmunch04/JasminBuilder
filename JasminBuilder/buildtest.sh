TitleCaseConverter() {
  sed 's/.*/\L&/; s/[a-z]*/\u&/g' <<< "$1"
}

# loop through all files in the out dir
for filename in ./out/*.j; do
  # generate class file from jasmin file
  java -jar ./res/jasmin.jar "$filename"

  # find the last filename: ./out/xxx.j -> xxx.j
  A="$(cut -d'/' -f3 <<< "$filename")"
  # remove the extension: xxx.j -> xxx
  B="$(cut -d'.' -f1 <<< "$A")"
  # convert it to title case (it already is)
  #C="$(TitleCaseConverter "$B")"

  # run the generated class file
  java "$B"

  # remove the generated class file
  rm "$B.class"
done