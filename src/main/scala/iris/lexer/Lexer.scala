package iris.lexer

import iris.exception.InvalidCharacter
import iris.reader.{Position, Reader}

class Lexer(reader: Reader):
	private var token: Either[InvalidCharacter, Token] = Left(InvalidCharacter(0))

	private def isValidWordChar(char: Char): Boolean =
		val pattern = "[!#$%&'*+\\-./<=>?@^`|~\\w]".r
		pattern.matches(char.toString)

	private def lexEof(): Unit =
		token = Right(Token(TokenKind.Eof, "", reader.pos))

	private def lexLeftParen(): Unit =
		token = Right(Token(TokenKind.LParen, "(", reader.pos))
		reader.next()

	private def lexRightParen(): Unit =
		token = Right(Token(TokenKind.RParen, ")", reader.pos))
		reader.next()

	private def lexWord(): Unit =
		val pos = reader.pos
		val str = StringBuilder()

		while reader.peek.isDefined && isValidWordChar(reader.peek.get) do
			str.append(reader.peek.get)
			reader.next()

		if str.isEmpty then
			token = Left(InvalidCharacter(reader.peek.get))
			return

		val lexeme = str.toString
		val kind =
			if lexeme.toIntOption.isDefined then
				TokenKind.Integer
			else if ReservedKeywords.contains(lexeme) then
				TokenKind.Keyword
			else
				TokenKind.Identifier

		token = Right(Token(kind, lexeme, pos))

	private def skipWhitespace(): Unit =
		while reader.peek.isDefined && reader.peek.get.isWhitespace do
			reader.next()

	def peek: Either[InvalidCharacter, Token] = token

	def next(): Unit =
		skipWhitespace()
		reader.peek match
			case Some('(') => lexLeftParen()
			case Some(')') => lexRightParen()
			case Some(char) => lexWord()
			case None => lexEof()

	next()
