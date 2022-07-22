package iris.reader

type Position = (Int, Int)

class Reader(input: String):
	private var index = 0
	private var line = 1
	private var col = 0

	def empty: Boolean = peek.isEmpty

	def pos: Position = (line, col)

	def peek: Option[Char] = if index < input.length then Some(input.charAt(index)) else None

	def next(): Unit =
		if peek == Some('\n') then
			line += 1
			col = 0
		else
			col += 1

		index += 1
