package iris.lexer

import scala.collection.immutable.HashSet

import iris.reader.Position

enum TokenKind:
	case LParen, RParen, Identifier, Keyword, Integer, Eof

case class Token(kind: TokenKind, lexeme: String, pos: Position)

val ReservedKeywords = HashSet(
	"let"
)
