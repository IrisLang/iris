package iris.lexer

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.shouldBe

import iris.reader.Reader

def makeLexer(input: String) = Lexer(Reader(input))

def assertLexerOutput(lexer: Lexer, output: List[Token]): Unit =
	for token <- output do
		lexer.peek shouldBe token
		lexer.next()

class LexerSpec extends AnyFlatSpec {
	"Lexer with empty input" should "only output EOF tokens" in {
		val lexer = makeLexer("")
		assertLexerOutput(lexer, List(
			Token(TokenKind.Eof, "", (1, 0))
		))
	}

	"Lexer" should "correctly lex parentheses" in {
		val lexer = makeLexer("(())")
		assertLexerOutput(lexer, List(
			Token(TokenKind.LParen, "(", (1, 0)),
			Token(TokenKind.LParen, "(", (1, 1)),
			Token(TokenKind.RParen, ")", (1, 2)),
			Token(TokenKind.RParen, ")", (1, 3)),
			Token(TokenKind.Eof, "", (1, 4))
		))
	}

	"Lexer" should "correctly lex identifiers" in {
		val lexer = makeLexer("ab 1a2 _0. ?? * ab/c' a-bc ^.^")
		assertLexerOutput(lexer, List(
			Token(TokenKind.Identifier, "ab", (1, 0)),
			Token(TokenKind.Identifier, "1a2", (1, 3)),
			Token(TokenKind.Identifier, "_0.", (1, 7)),
			Token(TokenKind.Identifier, "??", (1, 11)),
			Token(TokenKind.Identifier, "*", (1, 14)),
			Token(TokenKind.Identifier, "ab/c'", (1, 16)),
			Token(TokenKind.Identifier, "a-bc", (1, 22)),
			Token(TokenKind.Identifier, "^.^", (1, 27)),
			Token(TokenKind.Eof, "", (1, 30))
		))
	}

	"Lexer" should "correctly lex reserved keywords" in {
		val lexer = makeLexer("let")
		assertLexerOutput(lexer, List(
			Token(TokenKind.Keyword, "let", (1, 0))
		))
	}

	"Lexer" should "correctly lex integers" in {
		val lexer = makeLexer("30 -999 0")
		assertLexerOutput(lexer, List(
			Token(TokenKind.Integer, "30", (1, 0)),
			Token(TokenKind.Integer, "-999", (1, 3)),
			Token(TokenKind.Integer, "0", (1, 8)),
			Token(TokenKind.Eof, "", (1, 9))
		))
	}

	"Lexer" should "correctly lex sample source code" in {
		val lexer = makeLexer(
			"""
			(let (average a b)
				(/ (+ a b) 2))

			(average 30 3000)
			""".stripIndent.trim
		)

		assertLexerOutput(lexer, List(
			Token(TokenKind.LParen, "(", (1, 0)),
			Token(TokenKind.Keyword, "let", (1, 1)),
			Token(TokenKind.LParen, "(", (1, 5)),
			Token(TokenKind.Identifier, "average", (1, 6)),
			Token(TokenKind.Identifier, "a", (1, 14)),
			Token(TokenKind.Identifier, "b", (1, 16)),
			Token(TokenKind.RParen, ")", (1, 17)),
			Token(TokenKind.LParen, "(", (2, 1)),
			Token(TokenKind.Identifier, "/", (2, 2)),
			Token(TokenKind.LParen, "(", (2, 4)),
			Token(TokenKind.Identifier, "+", (2, 5)),
			Token(TokenKind.Identifier, "a", (2, 7)),
			Token(TokenKind.Identifier, "b", (2, 9)),
			Token(TokenKind.RParen, ")", (2, 10)),
			Token(TokenKind.Integer, "2", (2, 12)),
			Token(TokenKind.RParen, ")", (2, 13)),
			Token(TokenKind.RParen, ")", (2, 14)),
			Token(TokenKind.LParen, "(", (4, 0)),
			Token(TokenKind.Identifier, "average", (4, 1)),
			Token(TokenKind.Integer, "30", (4, 9)),
			Token(TokenKind.Integer, "3000", (4, 12)),
			Token(TokenKind.RParen, ")", (4, 16)),
			Token(TokenKind.Eof, "", (4, 17))
		))
	}
}
