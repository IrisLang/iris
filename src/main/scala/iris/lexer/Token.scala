package iris.lexer

import scala.collection.immutable.HashSet

import iris.reader.Position

enum TokenKind:
	case LParen, RParen, Identifier, Keyword, Integer, Eof, Unknown

case class Token(
	kind: TokenKind = TokenKind.Unknown,
	lexeme: String = "",
	pos: Position = (0, 0)
)

val ReservedKeywords = HashSet(
	"let"
)
