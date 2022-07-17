package iris.lexer

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class TokenSpec extends AnyFlatSpec with Matchers {
	"Token called with no arguments" should "construct a default token" in {
		Token() shouldBe Token(TokenKind.Unknown, "", (0, 0))
	}
}
