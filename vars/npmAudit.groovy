def call(body = null) {
	if (isUnix()) {
		sh label: "NPM Audit", script: "npm audit --json"
	} else {
		bat label: "NPM Audit", script: "npm audit --json"
	}
}