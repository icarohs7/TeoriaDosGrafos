/* Lista de plugins */
plugins {
	application
	java
	kotlin("jvm").version("1.2.41")
	id("org.jetbrains.dokka").version("0.9.16")
	id("com.github.gradle-git-version-calculator").version("1.1.0")
}

/* Dados do projeto: */
// Domínio
group = "teoriadosgrafos"

// Versão
version = gitVersionCalculator.calculateVersion().replace("\\+?\\.?\\d?dev".toRegex(), "")

// Descrição
description = "Projeto contendo todo o conteúdo da matéria Teoria dos Grafos" +
		" do curso de Ciência da Computação da Faculdade Única de Ipatinga," +
		" utilizado também para desenvolver os trabalhos avaliativos" +
		" referentes à matéria"

/* Definições da aplicação */
application {
	mainClassName = "teoriadosgrafos.aplicacao.trabalho2etapa2.Trabalho2E2Kt"
}

/* Configurações do Java */
java {
	sourceCompatibility = JavaVersion.VERSION_1_10
	targetCompatibility = JavaVersion.VERSION_1_10
}

/* Lista de dependências */
dependencies {
	implementation(kotlin("stdlib-jdk8"))
	implementation("com.miglayout:miglayout:3.7.4")
	implementation("com.jtattoo:JTattoo:1.6.11")
	implementation("com.github.icarohs7.unoxlib:UNoxLib:0.1.1")
}

/* Lista de repositórios */
repositories {
	jcenter()
	mavenCentral()
}

/* Tarefa de documentação através do dokka */
val dokka by tasks.getting(org.jetbrains.dokka.gradle.DokkaTask::class) {
	outputFormat = "javadoc"
	outputDirectory = "$projectDir/docs"
}

/* Tarefa de geração de fatJar */
@Suppress("IMPLICIT_CAST_TO_ANY")
val fatJar = task("fatJar", type = Jar::class) {
	baseName = "${project.name}-fat"
	manifest {
		attributes["Main-Class"] = application.mainClassName
	}
	from(
		configurations.runtime.map {
			if (it.isDirectory) it else zipTree(it)
		}
	)
	with(tasks["jar"] as CopySpec)
}

/* Configurações de tarefas */
tasks {
	"build" {
		dependsOn(fatJar)
	}
}
