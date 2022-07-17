package iris.lexer

import iris.reader.Position

enum TokenKind:
	case LParen, RParen, Identifier, Integer, Eof, Unknown

case class Token(
	kind: TokenKind = TokenKind.Unknown,
	lexeme: String = "",
	pos: Position = (0, 0)
)
