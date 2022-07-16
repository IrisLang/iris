package iris.cli

def AsciiBox(args: String*): String =
	val width = args.map(_.length).maxOption.getOrElse(0)
	val str = StringBuilder(s"┌${"─".repeat(width + 2)}┐\n")

	for arg <- args do
		str.append(s"│ ${arg.padTo(width, ' ')} │\n")

	str.append(s"└${"─".repeat(width + 2)}┘")
	str.toString
