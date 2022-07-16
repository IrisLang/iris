package iris.cli

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class AsciiBoxSpec extends AnyFlatSpec with Matchers {
	"AsciiBox called with no arguments" should "generate a 2 by 4 rectangle" in {
		AsciiBox() shouldBe
			"""
			┌──┐
			└──┘
			""".stripIndent.trim
	}

	"AsciiBox called with some arguments" should "generate a best-fit rectangle" in {
		AsciiBox("a") shouldBe
			"""
			┌───┐
			│ a │
			└───┘
			""".stripIndent.trim

		AsciiBox("a", "bcd", "", "ef") shouldBe
			"""
			┌─────┐
			│ a   │
			│ bcd │
			│     │
			│ ef  │
			└─────┘
			""".stripIndent.trim
	}
}
