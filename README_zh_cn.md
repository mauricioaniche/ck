# CK

CK 通过静态分析计算 Java 项目中的类级别和方法级别的代码度量（即无需编译代码）。目前，它包含一组丰富的度量指标，包括著名的 CK 指标：

* **CBO (对象间耦合度)**：计算类的依赖数量。工具会检查类中使用的所有类型（字段声明、方法返回类型、变量声明等）。它忽略对 Java 本身的依赖（例如 `java.lang.String`）。

* **CBO Modified (修改版对象间耦合度)**：计算类的依赖数量。与 CKTool 原始的 CBO 非常相似，但此度量考虑类的依赖关系时，既包括类对其他类型的引用，也包括它接收的来自其他类型的引用。

* **FAN-IN**：计算类的输入依赖数量，即引用某个类的其他类的数量。例如，给定一个类 X，X 的 FAN-IN 就是引用 X 的类的数量，无论是通过属性、访问属性，还是调用方法等。

* **FAN-OUT**：计算类的输出依赖数量，即某个类引用的其他类的数量。换句话说，给定类 X，X 的 FAN-OUT 就是 X 引用的类的数量，通过属性引用、方法调用、实例化对象等。

* **DIT (继承树深度)**：计算类的“父类”数量。所有类的 DIT 至少为 1（因为所有类都继承自 `java.lang.Object`）。如果类 A 依赖于类 X，X 又依赖于其他类，则 DIT 为 2。

* **NOC (子类数量)**：计算某个类的直接子类数量。

* **字段数量**：计算类中字段的数量。具体统计字段的总数、静态字段、公共字段、私有字段、受保护字段、默认字段、最终字段和同步字段。

* **方法数量**：计算类中方法的数量。具体统计方法的总数、静态方法、公共方法、抽象方法、私有方法、受保护方法、默认方法、最终方法和同步方法。构造方法也计入其中。

* **可见方法数量**：计算类中可见方法的数量。方法只有在不是私有的情况下才算作可见。

* **NOSI (静态调用数量)**：计算类中调用静态方法的次数。它只能统计那些能够通过 JDT 解析的静态方法调用。

* **RFC (类的响应集合)**：计算类中唯一方法调用的数量。由于静态分析的限制，当方法存在多个重载且参数数量相同但类型不同的情况时，这个度量会失败。

* **WMC (加权方法复杂度)** 或 McCabe 的复杂度：计算类中分支指令的数量。

* **LOC (代码行数)**：计算源代码的行数，忽略空行和注释（即源代码行数，或 SLOC）。这里的行数与原始文件可能会有所不同，因为我们使用了 JDT 的内部表示来计算代码行数。

* **LCOM (方法内聚性缺失度)**：计算 LCOM 指标。这个版本的度量并不完全可靠，LCOM-HS 更为精确（希望你能向我们提交一个 Pull Request）。

* **LCOM* (方法内聚性缺失度)**：这个度量是对 CK 工具中当前 LCOM 版本的修改版本。LCOM* 是一个归一化的度量，它将类的内聚性计算在 0 到 1 的范围内。值越接近 1，表示类的内聚性越差；值越接近 0，表示类的内聚性越强。该实现遵循 [1] 中定义的第三版 LCOM*。

    * 参考：[1] Henderson-Sellers, Brian, Larry L. Constantine 和 Ian M. Graham. “Coupling and cohesion (towards a valid metrics suite for object-oriented analysis and design).” Object Oriented Systems 3 (1996): 143-158。

* **TCC (紧密类内聚性)**：测量类的内聚性，值的范围从 0 到 1。TCC 通过类中可见方法之间的直接连接来测量内聚性，两个方法或它们的调用树访问相同的类变量时，表示它们之间存在内聚性。

* **LCC (松散类内聚性)**：类似于 TCC，但它进一步包括了可见类之间间接连接的数量。因此，LCC >= TCC 这一约束始终成立。

* **返回语句数量**：计算 `return` 语句的数量。

* **循环语句数量**：计算循环语句的数量（例如：`for`、`while`、`do while`、增强型 `for`）。

* **比较语句数量**：计算比较操作符的数量（例如：`==` 和 `!=`）。注意：`!=` 仅在 0.4.2 及以上版本中可用。

* **try/catch 语句数量**：计算 `try/catch` 语句的数量。

* **括号表达式数量**：计算括号中的表达式数量。

* **字符串字面量数量**：计算字符串字面量的数量（例如：`"John Doe"`）。重复的字符串会根据出现次数计算。

* **数字字面量数量**：计算数字字面量的数量（例如：`int`、`long`、`double`、`float`）。

* **数学运算数量**：计算数学运算（乘法、除法、余数、加法、减法、左移、右移）的数量。

* **变量数量**：计算声明的变量数量。

* **最大嵌套块数**：计算最大嵌套块的数量。

* **匿名类、内部类和 lambda 表达式数量**：名称说明一切。请注意，每当声明一个匿名类或内部类时，它就成为一个“完整的新类”。例如，CK 会生成 `A.B` 和 `A.B$C`，其中 C 是 A.B 内的内部类。然而，lambda 表达式不被视为类，因此它们属于嵌入其中的类或方法。每个类或方法只计算它所在级别声明的内部类数量。

* **唯一词数量**：计算源代码中唯一词汇的数量。方法级别只使用方法体作为输入，类级别使用类体的所有内容作为度量。算法基本上是计算方法/类中去除 Java 关键字后的词汇数量。名称会根据驼峰命名和下划线分隔（例如，`longName_likeThis` 被分为四个词）。请参见 `WordCounter` 类，了解详细的实现。

* **日志语句数量**：计算源代码中的日志语句数量。计数使用与 SLF4J 和 Log4J API 调用兼容的正则表达式。详情请参见 `NumberOfLogStatements.java` 和测试示例（`NumberOfLogStatementsTest` 以及 `fixtures/logs`）。

* **是否有 Javadoc**：布尔值，表示方法是否有 Javadoc（目前只适用于方法级别）。

* **修饰符**：类/方法的 `public`、`abstract`、`private`、`protected`、`native` 修饰符。可以通过 `org.eclipse.jdt.core.dom.Modifier` 解码。

* **每个变量的使用情况**：每个变量在方法内部的使用频率。

* **每个字段的使用情况**：每个字段在方法内部的使用频率，局部字段是类中的字段（不包括子类）。还可以检测到间接使用的局部字段，间接使用包括在类的本地调用树中对字段的所有使用，例如：A 调用 B，B 使用字段 `a`，则 A 间接使用了字段 `a`。

* **方法调用数量**：所有直接调用的方法，分为本地调用和间接本地调用。

注意：CK 会分开类、内部类和匿名类。LOC 是唯一一个与其他度量不完全隔离的度量。例如，如果类 A 声明了一个内部类 B，则 LOC(A) = LOC(class A) + LOC(inner class B)。

### 项目结构
CK 是一个用于收集 Java 代码度量的工具，其结构简单，主要围绕以下三个核心包：

- **ck**: 包含用于驱动度量收集过程的核心类。
- **ck.metrics**: 包含度量的定义与实现。
- **ck.utils**: 提供支持度量收集过程的实用工具。

为了简洁起见，在本文档中省略了包前缀（如 `com.github.mauricioaniche.ck`）。

---

### 架构
#### 核心组件
- **CK**: 工具的核心类，负责协调整个度量收集过程。它初始化度量收集器、根据可用内存对文件进行分区、设置带有适当环境配置的 AST 解析器，并管理不同目录和 JAR 依赖的执行流程。CK 会根据用户输入（如 `useJars`、`maxAtOnce` 和 `variablesAndFields`）动态调整其行为，以优化 Java 文件的度量收集。

- **Runner**: 应用程序的入口点，位于 `ck` 包中。该类处理命令行参数，用于配置和启动度量收集过程。它管理项目路径、JAR 包的引入、文件分区、度量详情以及输出目录的设置。Runner 通过初始化并利用 CK 类，以及通过 ResultWriter 处理结果输出，来协调整体执行过程。

- **MetricsExecutor**: 该类扩展了 Eclipse JDT（Java 开发工具）核心组件 `FileASTRequestor`，在 CK 框架中扮演重要角色，协调度量收集过程。MetricsExecutor 负责为 Java 源文件创建抽象语法树（AST），这是分析和提取代码度量的关键。

---

#### 度量识别
- **MetricsFinder**: 位于 `ck.utils` 中的实用类，在 CK 框架中扮演动态识别和实例化度量收集器类的重要角色。它定位实现了 `ClassLevelMetric` 和 `MethodLevelMetric` 接口的类，这些接口来自 `ck.metrics` 包。

  MetricsFinder 使用 Reflections 库在运行时扫描并加载度量收集器类，从而使 CK 系统能够扩展和适应新度量，而无需修改核心架构。这一特性特别适合将自定义度量无缝集成到分析过程中。

---

#### 度量收集
- **CKVisitor**: CK 框架的核心组件之一，继承了 Eclipse JDT 库提供的 `ASTVisitor` 类，使其能够直接从 Java 源代码的抽象语法树（AST）中进行详细分析和度量收集。

  CKVisitor 被设计为遍历 AST 的各种节点（如类型和方法），并在每个节点执行特定操作。它有效地管理基于堆栈的类和方法层次结构，使度量能够在当前节点范围内被计算和收集。

- **CKASTVisitor**: 在 `ck.metrics` 中由度量类实现，允许每个度量处理特定的 AST 节点，例如方法调用和类实例创建。

- **ClassLevelMetric 和 MethodLevelMetric**: 定义分别用于收集类级别和方法级别度量的方法的接口。

---

#### 结果通知与存储
- **MetricsExecutor**: 在完成度量收集后，通过使用 `CKNotifier` 接口的通知器设计模式广播结果。
- **匿名类（Runner.main 方法内）**: 使用收集的数据填充 `CKClassResult` 和 `CKMethodResult`。
- **ResultWriter**: 利用收集的数据生成并存储 `.CSV` 格式的结果。

---

### 设计模式
CK 框架采用了一些成熟的设计模式，以增强代码库的模块化、可扩展性和可维护性。这些模式使框架能够高效处理复杂操作，如遍历抽象语法树（AST）、收集度量和通知结果。以下是使用的关键设计模式：

- **访问者模式（Visitor Pattern）**:  
  CKVisitor 和 CKASTVisitor 接口实现了访问者模式，该模式在无需更改元素类的情况下处理各种 AST 节点上的操作。访问者模式特别适合在需要对 AST 节点类层次结构执行多种不同且无关的操作时使用。它通过将操作逻辑外部化到访问者对象中，简化了代码，并使得添加新操作变得容易。AST 节点结构与操作之间的解耦提高了代码的可维护性和可扩展性。

- **通知者模式（Notifier Pattern）**:  
  CK 通过使用 `CKNotifier` 采用了通知者模式，该模式充当广播度量收集结果的中央机制。通知者模式对于创建松耦合架构至关重要，在这种架构中，主题（度量计算过程）独立于其观察者（结果处理器或报告生成器）。这使得 CK 能够在完成度量计算后通知多个组件，而无需与特定组件耦合，从而增强了系统的灵活性和可扩展性。

- **工厂模式（Factory Pattern）**:  
  度量收集器的实例化由 MetricsFinder 管理，它体现了工厂模式。该模式用于封装基于运行时决策实例化特定度量收集器类的逻辑。工厂模式简化了添加新类型度量收集器的过程，而无需修改现有代码，从而提供了一种即插即用的架构，可以无缝引入新度量。它还帮助保持关注点分离，因为度量对象的创建过程与度量收集的核心逻辑是独立的。

通过利用这些设计模式，CK 高效地管理复杂性，并确保框架在新需求和度量类型出现时仍然稳健、适应性强且易于扩展。


## 如何使用独立版本

你需要至少 Java 8 才能编译和运行此工具。

要使用 _最新版本_（强烈建议使用），克隆项目并生成 JAR 文件。只需运行：
`mvn clean compile package`，即可生成单个 JAR 文件（请查看 _target_ 文件夹）。

然后，只需运行：

```
java -jar ck-x.x.x-SNAPSHOT-jar-with-dependencies.jar \
	<project dir> \
	<use jars:true|false> \
	<max files per partition:0=automatic selection> \
	<variables and fields metrics?:true|false> \
	<output dir> \
	[ignored directories...]
```

`项目目录` 指的是 CK 可以找到所有要解析的源代码的目录。CK 会递归查找 `.java` 文件。CK 可以使用项目的依赖关系来提高精度。`是否使用 JARs` 参数告诉 CK 是否查找该目录中的 `.jar` 文件并使用它们来更好地解析类型。`每个分区的最大文件数` 参数告诉 JDT 要处理的批次大小。我们会为你决定并默认设置为 0；如果出现问题（例如内存不足），你可以考虑调整它。`变量和字段度量` 表示你是否希望计算变量和字段级别的度量。它们非常细粒度，会生成大量输出；如果你只需要类级别或方法级别的度量，可以跳过此项。最后，`输出目录` 是 CK 导出分析项目的度量 CSV 文件的目录。你还可以选择性地指定任意数量的忽略目录，用空格分隔（例如，`build/`）。默认情况下，`.git` 和所有其他隐藏文件夹会被忽略。

该工具将生成三个 CSV 文件：类级别、方法级别和变量级别的度量。

## 如何将其集成到我的 Java 应用中

通过示例学习。请查看 `Runner.java` 类。

## Maven

查看本 README 开头的徽章，或访问 https://mvnrepository.com/artifact/com.github.mauricioaniche/ck，获取该库的最新版本。

在你的 `pom.xml` 中使用以下代码片段。将 X.Y.Z 替换为工具的最新版本（可以检查 mvnrepository.com 或 README 文件开头的徽章）：

```
<!-- https://mvnrepository.com/artifact/com.github.mauricioaniche/ck -->
<dependency>
    <groupId>com.github.mauricioaniche</groupId>
    <artifactId>ck</artifactId>
    <version>X.Y.Z</version>
</dependency>
```

你还可以使用 CK Maven 插件，由 @jazzmuesli 开发，它可以自动在你的项目中运行 CK。对于开发者非常有用：https://github.com/jazzmuesli/ck-mvn-plugin。

## 支持新的 Java 版本

此工具使用 Eclipse 的 JDT Core 库来构建 AST。目前的兼容版本设置为 Java 11。

需要支持更高版本的语言吗？添加支持的过程非常简单，考虑提交 PR：

1. 添加一个失败的单元测试用例，展示你希望支持的新版 Java 中的至少一种语法特性。
2. 在 `pom.xml` 文件中更新 Eclipse JDT Core 的依赖项。你可以使用类似 [MVN Repository](https://mvnrepository.com/artifact/org.eclipse.jdt/org.eclipse.jdt.core) 的仓库浏览器来简化这一过程。
3. 同样在 `pom.xml` 文件中，更新 Maven 编译插件的 `source` 和 `target` 属性。
4. 调整 `CK.java` 中的以下代码：
    ```
    [...]
    ASTParser parser = ASTParser.newParser(AST.JLS11);
    [...]
    JavaCore.setComplianceOptions(JavaCore.VERSION_11, options);
    [...]
    ```
5. 检查你在第一步中添加的失败的单元测试用例是否变为绿色。如果是，则提交 PR。

## 为什么叫 CK？

因为这个工具最初只是为了计算 CK 类级别的度量而诞生的，但它超出了我的预期... 生活就是这么有趣！

## 如何引用？

请使用以下 bibtex 条目：

```
@manual{aniche-ck,
  title={Java code metrics calculator (CK)},
  author={Maurício Aniche},
  year={2015},
  note={Available in https://github.com/mauricioaniche/ck/}
}
```

## 如何贡献

只需提交 PR！ :)

## 许可协议

该软件采用 [Apache 2.0 许可协议](LICENSE)。

