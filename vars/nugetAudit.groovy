def call(String auditTarget = 'nuget') {
	def patterns = [
		nuget:'**/*.csproj',
		netcore: '**/packages.config'
	
	
	def pattern = patterns[auditTarget]

	if (pattern != null) {
		def files = findFiles(glob: pattern)

		def auditHome = tool name: 'DevAudit', type: 'com.cloudbees.jenkins.plugins.customtools.CustomTool'

		for(file in files) {
			if (isUnix()) {
				sh label: "Nuget Audit: ${file}", script: "mono ${auditHome}/DevAudit/devaudit.exe ${auditTarget} --file ${file} --ci --non-interactive"
			} else {
				bat label: "Nuget Audit: ${file}", script: "${auditHome}/DevAudit/devaudit ${auditTarget} --file ${file} --ci --non-interactive"
			}
		}
	}
}