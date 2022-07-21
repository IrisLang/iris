package iris.exception

sealed trait IrisException
case class InvalidCharacter(char: Char) extends IrisException
