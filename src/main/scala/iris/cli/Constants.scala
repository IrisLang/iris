package iris.cli

import iris.BuildInfo

object Constants:
	val REPL_WELCOME_MSG = AsciiBox(
		s"Iris v${BuildInfo.version}",
		"Coming soon!"
	)
