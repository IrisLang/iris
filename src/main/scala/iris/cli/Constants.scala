package iris.cli

import iris.BuildInfo

object Constants:
	val ReplWelcomeMsg = AsciiBox(
		s"Iris v${BuildInfo.version}",
		"Coming soon!"
	)
