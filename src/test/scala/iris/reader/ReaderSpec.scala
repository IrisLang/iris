package iris.reader

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.shouldBe

def assertReaderOutput(reader: Reader, output: List[(Char, Position)]): Unit =
	for (c, pos) <- output do
		reader.peek shouldBe Some(c)
		reader.pos shouldBe pos
		reader.next()

	reader.empty shouldBe true

class ReaderSpec extends AnyFlatSpec {
	"Reader with empty input" should "output nothing" in {
		val reader = Reader("")
		assertReaderOutput(reader, List())
	}

	"Reader with single line input" should "output correct characters along with their positions" in {
		val reader = Reader(
			"""
			ab c
			""".stripIndent.trim
		)

		assertReaderOutput(reader, List(
			('a', (1, 0)),
			('b', (1, 1)),
			(' ', (1, 2)),
			('c', (1, 3))
		))
	}

	"Reader with multiline input" should "output correct characters along with their positions" in {
		val reader = Reader(
			"""
			a
				b

			 c
			d
			""".stripIndent.trim
		)

		assertReaderOutput(reader, List(
			('a', (1, 0)),
			('\n', (1, 1)),
			('\t', (2, 0)),
			('b', (2, 1)),
			('\n', (2, 2)),
			('\n', (3, 0)),
			(' ', (4, 0)),
			('c', (4, 1)),
			('\n', (4, 2)),
			('d', (5, 0))
		))
	}
}
