# FelisCatus Zero 
## Abstract
FelisCatus Zero answers world history essay questions in Japanese and evaluate the answers.  
The multilingual version (currently Japanese and English) is <a href="https://github.com/ktr-skmt/FelisCatusZero-multilingual">here</a>.
 
## Platform
* Unix Terminal
* Oracle Java SE Development Kit 8
* Scala 2.12.1
* Simple Build Tool 0.13.13
* MeCab 0.996
* UniDic unidic-mecab 2.1.2
* Indri 5.11

## Quick Start Guide
1. Get the QA corpus (pairs of question and gold standard) and the knowledge source
 - You can get both of the QA corpus and the knowledge source distributed by NTCIR [QA Lab task](http://research.nii.ac.jp/qalab/) Japanese subtask, if you participate in it and submit the agreement to NTCIR
 - Even if you do not have the QA corpus or the knowledge source, you can try to run the pipeline from Essay Generator as a trial. See below.
1. Read [Install Guide](https://github.com/ktr-skmt/FelisCatusZero/wiki/Install-Guide) and install the softwares.
1. Git clone or download this repository.
1. Read [Knowledge Source](https://github.com/ktr-skmt/FelisCatusZero/wiki/Knowledge-Source) and install the knowledge source.
1. Read [QA Corpus](https://github.com/ktr-skmt/FelisCatusZero/wiki/QA-Corpus) and install the QA corpus.
1. Read the "Run whole Pipeline" in [How to Run](https://github.com/ktr-skmt/FelisCatusZero/wiki/How-to-Run), and run FelisCatus Zero.

## Trial
Even if you did not have the QA corpus or the knowledge source, you can try to run the pipeline from Essay Generation with using the gzipped XMI files as a trial.

1. Install Scala and sbt.
1. Git clone or download this repository.
1. Run [trial.sh](trial.sh)

```bash
$ bash trial.sh
```

## Quick System Guide (in the style of [PPAP](https://www.youtube.com/watch?v=0E00Zuayv9Q))
I have datasets (of questions and gold standards): [src/main/resources/qaset](https://github.com/ktr-skmt/FelisCatusZero/tree/master/src/main/resources/qaset)  
I have a command: [tutorial/run.ipynb](https://github.com/ktr-skmt/FelisCatusZero/blob/master/tutorial/run.ipynb)

Ughhh

Essays and evaluation results!: [tutorial/out_example/result](https://github.com/ktr-skmt/FelisCatusZero/tree/master/tutorial/out_example/result)

<a href="https://github.com/ktr-skmt/FelisCatusZero/blob/master/tutorial/image/pipeline.png?raw=true" target="_blank"><img src="https://github.com/ktr-skmt/FelisCatusZero/blob/master/tutorial/image/pipeline.png?raw=true" width="500px"/></a>

## More ...
Go to [Wiki](https://github.com/ktr-skmt/FelisCatusZero/wiki/)!
