package iris.lexer

import iris.reader.{Position, Reader}

class Lexer(reader: Reader):
	private var token: Token = Token()

	private def isValidWordChar(char: Char): Boolean =
		val pattern = "[!#$%&'*+\\-./<=>?@^`|~\\w]".r
		pattern.matches(char.toString)

	private def lexEof(): Unit =
		token = Token(TokenKind.Eof, "", reader.pos)

	private def lexParen(): Unit =
		reader.peek.get match
			case '(' => token = Token(TokenKind.LParen, "(", reader.pos)
			case ')' => token = Token(TokenKind.RParen, ")", reader.pos)

		reader.next()

	private def lexUnknown(char: Char): Unit =
		token = Token(TokenKind.Unknown, char.toString, reader.pos)

	private def lexWord(): Unit =
		val pos = reader.pos
		val str = StringBuilder()

		while reader.peek.isDefined && isValidWordChar(reader.peek.get) do
			str.append(reader.peek.get)
			reader.next()

		val lexeme = str.toString
		val kind = if lexeme.toIntOption.isDefined then TokenKind.Integer else TokenKind.Identifier
		token = Token(kind, lexeme, pos)

	private def skipWhitespace(): Unit =
		while reader.peek.isDefined && reader.peek.get.isWhitespace do
			reader.next()

	def peek: Token = token

	def next(): Unit =
		skipWhitespace()
		reader.peek match
			case Some('(') | Some(')') => lexParen()
			case Some(char) => if isValidWordChar(char) then lexWord() else lexUnknown(char)
			case None => lexEof()

	next()
