package iris.lexer

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.shouldBe

class TokenSpec extends AnyFlatSpec {
	"Token called with no arguments" should "construct a default token" in {
		Token() shouldBe Token(TokenKind.Eof, "", (0, 0))
	}
}
