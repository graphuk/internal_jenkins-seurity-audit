def call(body = null) {
	def projFiles = findFiles(glob: '**/*.csproj')
	def packageFiles = findFiles(glob: '**/packages.config')
	def files = projFiles + packageFiles
	def auditHome = tool name: 'DevAudit', type: 'com.cloudbees.jenkins.plugins.customtools.CustomTool'

	for(file in files) {
		bat label: "Nuget Audit: ${file}", script: "${auditHome}/DevAudit/devaudit nuget --file ${file}--help --ci --non-interactive"
	}
}