package iris.exception

import iris.reader.Position

sealed trait IrisException

case class InvalidCharacter(
	pos: Position,
	char: Char
) extends IrisException:
	override def toString(): String = s"invalid character '$char' found at $pos"
