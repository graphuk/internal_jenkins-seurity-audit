def call(body = null) {
	def projFiles = findFiles(glob: '**/*.csproj')
	def packageFiles = findFiles(glob: '**/packages.config')
	def files = projFiles + packageFiles
	def auditHome = tool name: 'DevAudit', type: 'com.cloudbees.jenkins.plugins.customtools.CustomTool'

	for(file in files) {
		if (isUnix()) {
			sh label: "Nuget Audit: ${file}", script: "mono ${auditHome}/DevAudit/devaudit.exe nuget --file ${file} --ci --non-interactive"
		} else {
			bat label: "Nuget Audit: ${file}", script: "${auditHome}/DevAudit/devaudit nuget --file ${file} --ci --non-interactive"
		}
	}
}