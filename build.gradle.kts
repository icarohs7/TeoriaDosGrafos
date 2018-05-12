/* Dados do projeto: */
allprojects {
	/* Domínio */
	group = "teoriadosgrafos"
	/* Descrição */
	description = "Projeto contendo todo o conteúdo da matéria Teoria dos Grafos" +
			" do curso de Ciência da Computação da Faculdade Única de Ipatinga," +
			" utilizado também para desenvolver os trabalhos avaliativos" +
			" referentes à matéria"
	
	/**
	 * Lista de dependências
	 */
	dependencies {
		implementation(kotlin("stdlib-jdk8"))
		implementation("com.miglayout:miglayout:3.7.4")
		implementation("com.jtattoo:JTattoo:1.6.11")
		implementation("com.github.icarohs7.unoxlib:UNoxLib:0.1.1")
	}
}

/**
 * Lista de plugins
 */
plugins {
	application
	java
	kotlin("jvm") version "1.2.41"
	id("org.jetbrains.dokka") version "0.9.16"
}

/**
 * Definições da aplicação
 */
application {
	mainClassName = "teoriadosgrafos.aplicacao.trabalho2etapa2.Trabalho2Etapa2"
}

/**
 * Configurações do Java
 */
java {
	sourceCompatibility = JavaVersion.VERSION_1_10
	targetCompatibility = JavaVersion.VERSION_1_10
}

/**
 * Lista de repositórios
 */
repositories {
	jcenter()
	mavenCentral()
}

/**
 * Definição do autoversionamento
 */
autoversion {
	versionFile = "version.json"
}

/**
 * Definir tarefa de geração de documentação utilizando dokka
 */
val dokka by tasks.getting(org.jetbrains.dokka.gradle.DokkaTask::class) {
	outputFormat = "javadoc"
	outputDirectory = "$buildDir/docs"
}

/**
 * Definir tarefa de geração de jar
 */
tasks.withType<Jar> {
	configurations["compileClasspath"].forEach { file: File ->
		from(zipTree(file.absoluteFile))
	}
}


