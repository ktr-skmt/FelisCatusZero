

/* First created by JCasGen Wed Jan 25 19:24:52 JST 2017 */
package jeqa.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.cas.FSList;
import org.apache.uima.jcas.cas.TOP;
import org.apache.uima.jcas.cas.StringList;


/** Examで出題されたQuestion
 * Updated by JCasGen Wed Jan 25 19:24:52 JST 2017
 * XML source: src/main/resources/desc/ts/typeSystem.xml
 * @generated */
public class Question extends TOP {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Question.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected Question() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Question(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Question(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** 
   * <!-- begin-user-doc -->
   * Write your own initialization here
   * <!-- end-user-doc -->
   *
   * @generated modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: xml

  /** getter for xml - gets XML Answer Section
   * @generated
   * @return value of the feature 
   */
  public String getXml() {
    if (Question_Type.featOkTst && ((Question_Type)jcasType).casFeat_xml == null)
      jcasType.jcas.throwFeatMissing("xml", "jeqa.types.Question");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Question_Type)jcasType).casFeatCode_xml);}
    
  /** setter for xml - sets XML Answer Section 
   * @generated
   * @param v value to set into the feature 
   */
  public void setXml(String v) {
    if (Question_Type.featOkTst && ((Question_Type)jcasType).casFeat_xml == null)
      jcasType.jcas.throwFeatMissing("xml", "jeqa.types.Question");
    jcasType.ll_cas.ll_setStringValue(addr, ((Question_Type)jcasType).casFeatCode_xml, v);}    
   
    
  //*--------------*
  //* Feature: document

  /** getter for document - gets 問題文
   * @generated
   * @return value of the feature 
   */
  public Document getDocument() {
    if (Question_Type.featOkTst && ((Question_Type)jcasType).casFeat_document == null)
      jcasType.jcas.throwFeatMissing("document", "jeqa.types.Question");
    return (Document)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Question_Type)jcasType).casFeatCode_document)));}
    
  /** setter for document - sets 問題文 
   * @generated
   * @param v value to set into the feature 
   */
  public void setDocument(Document v) {
    if (Question_Type.featOkTst && ((Question_Type)jcasType).casFeat_document == null)
      jcasType.jcas.throwFeatMissing("document", "jeqa.types.Question");
    jcasType.ll_cas.ll_setRefValue(addr, ((Question_Type)jcasType).casFeatCode_document, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: label

  /** getter for label - gets 個々のQuestionにつけられたユニークなラベル
   * @generated
   * @return value of the feature 
   */
  public String getLabel() {
    if (Question_Type.featOkTst && ((Question_Type)jcasType).casFeat_label == null)
      jcasType.jcas.throwFeatMissing("label", "jeqa.types.Question");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Question_Type)jcasType).casFeatCode_label);}
    
  /** setter for label - sets 個々のQuestionにつけられたユニークなラベル 
   * @generated
   * @param v value to set into the feature 
   */
  public void setLabel(String v) {
    if (Question_Type.featOkTst && ((Question_Type)jcasType).casFeat_label == null)
      jcasType.jcas.throwFeatMissing("label", "jeqa.types.Question");
    jcasType.ll_cas.ll_setStringValue(addr, ((Question_Type)jcasType).casFeatCode_label, v);}    
   
    
  //*--------------*
  //* Feature: questionFormatType

  /** getter for questionFormatType - gets Question Format Type
   * @generated
   * @return value of the feature 
   */
  public String getQuestionFormatType() {
    if (Question_Type.featOkTst && ((Question_Type)jcasType).casFeat_questionFormatType == null)
      jcasType.jcas.throwFeatMissing("questionFormatType", "jeqa.types.Question");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Question_Type)jcasType).casFeatCode_questionFormatType);}
    
  /** setter for questionFormatType - sets Question Format Type 
   * @generated
   * @param v value to set into the feature 
   */
  public void setQuestionFormatType(String v) {
    if (Question_Type.featOkTst && ((Question_Type)jcasType).casFeat_questionFormatType == null)
      jcasType.jcas.throwFeatMissing("questionFormatType", "jeqa.types.Question");
    jcasType.ll_cas.ll_setStringValue(addr, ((Question_Type)jcasType).casFeatCode_questionFormatType, v);}    
   
    
  //*--------------*
  //* Feature: answerFormatType

  /** getter for answerFormatType - gets Answer Format Type
   * @generated
   * @return value of the feature 
   */
  public String getAnswerFormatType() {
    if (Question_Type.featOkTst && ((Question_Type)jcasType).casFeat_answerFormatType == null)
      jcasType.jcas.throwFeatMissing("answerFormatType", "jeqa.types.Question");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Question_Type)jcasType).casFeatCode_answerFormatType);}
    
  /** setter for answerFormatType - sets Answer Format Type 
   * @generated
   * @param v value to set into the feature 
   */
  public void setAnswerFormatType(String v) {
    if (Question_Type.featOkTst && ((Question_Type)jcasType).casFeat_answerFormatType == null)
      jcasType.jcas.throwFeatMissing("answerFormatType", "jeqa.types.Question");
    jcasType.ll_cas.ll_setStringValue(addr, ((Question_Type)jcasType).casFeatCode_answerFormatType, v);}    
   
    
  //*--------------*
  //* Feature: querySet

  /** getter for querySet - gets QuestionがIRに投げたいQueryのセット
   * @generated
   * @return value of the feature 
   */
  public FSArray getQuerySet() {
    if (Question_Type.featOkTst && ((Question_Type)jcasType).casFeat_querySet == null)
      jcasType.jcas.throwFeatMissing("querySet", "jeqa.types.Question");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Question_Type)jcasType).casFeatCode_querySet)));}
    
  /** setter for querySet - sets QuestionがIRに投げたいQueryのセット 
   * @generated
   * @param v value to set into the feature 
   */
  public void setQuerySet(FSArray v) {
    if (Question_Type.featOkTst && ((Question_Type)jcasType).casFeat_querySet == null)
      jcasType.jcas.throwFeatMissing("querySet", "jeqa.types.Question");
    jcasType.ll_cas.ll_setRefValue(addr, ((Question_Type)jcasType).casFeatCode_querySet, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for querySet - gets an indexed value - QuestionがIRに投げたいQueryのセット
   * @generated
   * @param i index in the array to get
   * @return value of the element at index i 
   */
  public Query getQuerySet(int i) {
    if (Question_Type.featOkTst && ((Question_Type)jcasType).casFeat_querySet == null)
      jcasType.jcas.throwFeatMissing("querySet", "jeqa.types.Question");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((Question_Type)jcasType).casFeatCode_querySet), i);
    return (Query)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((Question_Type)jcasType).casFeatCode_querySet), i)));}

  /** indexed setter for querySet - sets an indexed value - QuestionがIRに投げたいQueryのセット
   * @generated
   * @param i index in the array to set
   * @param v value to set into the array 
   */
  public void setQuerySet(int i, Query v) { 
    if (Question_Type.featOkTst && ((Question_Type)jcasType).casFeat_querySet == null)
      jcasType.jcas.throwFeatMissing("querySet", "jeqa.types.Question");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((Question_Type)jcasType).casFeatCode_querySet), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((Question_Type)jcasType).casFeatCode_querySet), i, jcasType.ll_cas.ll_getFSRef(v));}
   
    
  //*--------------*
  //* Feature: keywordSet

  /** getter for keywordSet - gets キーワード集合
   * @generated
   * @return value of the feature 
   */
  public FSList getKeywordSet() {
    if (Question_Type.featOkTst && ((Question_Type)jcasType).casFeat_keywordSet == null)
      jcasType.jcas.throwFeatMissing("keywordSet", "jeqa.types.Question");
    return (FSList)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Question_Type)jcasType).casFeatCode_keywordSet)));}
    
  /** setter for keywordSet - sets キーワード集合 
   * @generated
   * @param v value to set into the feature 
   */
  public void setKeywordSet(FSList v) {
    if (Question_Type.featOkTst && ((Question_Type)jcasType).casFeat_keywordSet == null)
      jcasType.jcas.throwFeatMissing("keywordSet", "jeqa.types.Question");
    jcasType.ll_cas.ll_setRefValue(addr, ((Question_Type)jcasType).casFeatCode_keywordSet, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: beginCharacterLimit

  /** getter for beginCharacterLimit - gets 何文字以上
   * @generated
   * @return value of the feature 
   */
  public int getBeginCharacterLimit() {
    if (Question_Type.featOkTst && ((Question_Type)jcasType).casFeat_beginCharacterLimit == null)
      jcasType.jcas.throwFeatMissing("beginCharacterLimit", "jeqa.types.Question");
    return jcasType.ll_cas.ll_getIntValue(addr, ((Question_Type)jcasType).casFeatCode_beginCharacterLimit);}
    
  /** setter for beginCharacterLimit - sets 何文字以上 
   * @generated
   * @param v value to set into the feature 
   */
  public void setBeginCharacterLimit(int v) {
    if (Question_Type.featOkTst && ((Question_Type)jcasType).casFeat_beginCharacterLimit == null)
      jcasType.jcas.throwFeatMissing("beginCharacterLimit", "jeqa.types.Question");
    jcasType.ll_cas.ll_setIntValue(addr, ((Question_Type)jcasType).casFeatCode_beginCharacterLimit, v);}    
   
    
  //*--------------*
  //* Feature: endCharacterLimit

  /** getter for endCharacterLimit - gets 何文字以下
   * @generated
   * @return value of the feature 
   */
  public int getEndCharacterLimit() {
    if (Question_Type.featOkTst && ((Question_Type)jcasType).casFeat_endCharacterLimit == null)
      jcasType.jcas.throwFeatMissing("endCharacterLimit", "jeqa.types.Question");
    return jcasType.ll_cas.ll_getIntValue(addr, ((Question_Type)jcasType).casFeatCode_endCharacterLimit);}
    
  /** setter for endCharacterLimit - sets 何文字以下 
   * @generated
   * @param v value to set into the feature 
   */
  public void setEndCharacterLimit(int v) {
    if (Question_Type.featOkTst && ((Question_Type)jcasType).casFeat_endCharacterLimit == null)
      jcasType.jcas.throwFeatMissing("endCharacterLimit", "jeqa.types.Question");
    jcasType.ll_cas.ll_setIntValue(addr, ((Question_Type)jcasType).casFeatCode_endCharacterLimit, v);}    
   
    
  //*--------------*
  //* Feature: beginTimeLimit

  /** getter for beginTimeLimit - gets begin time limit
   * @generated
   * @return value of the feature 
   */
  public Time getBeginTimeLimit() {
    if (Question_Type.featOkTst && ((Question_Type)jcasType).casFeat_beginTimeLimit == null)
      jcasType.jcas.throwFeatMissing("beginTimeLimit", "jeqa.types.Question");
    return (Time)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Question_Type)jcasType).casFeatCode_beginTimeLimit)));}
    
  /** setter for beginTimeLimit - sets begin time limit 
   * @generated
   * @param v value to set into the feature 
   */
  public void setBeginTimeLimit(Time v) {
    if (Question_Type.featOkTst && ((Question_Type)jcasType).casFeat_beginTimeLimit == null)
      jcasType.jcas.throwFeatMissing("beginTimeLimit", "jeqa.types.Question");
    jcasType.ll_cas.ll_setRefValue(addr, ((Question_Type)jcasType).casFeatCode_beginTimeLimit, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: endTimeLimit

  /** getter for endTimeLimit - gets end time limit
   * @generated
   * @return value of the feature 
   */
  public Time getEndTimeLimit() {
    if (Question_Type.featOkTst && ((Question_Type)jcasType).casFeat_endTimeLimit == null)
      jcasType.jcas.throwFeatMissing("endTimeLimit", "jeqa.types.Question");
    return (Time)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Question_Type)jcasType).casFeatCode_endTimeLimit)));}
    
  /** setter for endTimeLimit - sets end time limit 
   * @generated
   * @param v value to set into the feature 
   */
  public void setEndTimeLimit(Time v) {
    if (Question_Type.featOkTst && ((Question_Type)jcasType).casFeat_endTimeLimit == null)
      jcasType.jcas.throwFeatMissing("endTimeLimit", "jeqa.types.Question");
    jcasType.ll_cas.ll_setRefValue(addr, ((Question_Type)jcasType).casFeatCode_endTimeLimit, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: geographyLimit

  /** getter for geographyLimit - gets geography limit
   * @generated
   * @return value of the feature 
   */
  public Geography getGeographyLimit() {
    if (Question_Type.featOkTst && ((Question_Type)jcasType).casFeat_geographyLimit == null)
      jcasType.jcas.throwFeatMissing("geographyLimit", "jeqa.types.Question");
    return (Geography)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Question_Type)jcasType).casFeatCode_geographyLimit)));}
    
  /** setter for geographyLimit - sets geography limit 
   * @generated
   * @param v value to set into the feature 
   */
  public void setGeographyLimit(Geography v) {
    if (Question_Type.featOkTst && ((Question_Type)jcasType).casFeat_geographyLimit == null)
      jcasType.jcas.throwFeatMissing("geographyLimit", "jeqa.types.Question");
    jcasType.ll_cas.ll_setRefValue(addr, ((Question_Type)jcasType).casFeatCode_geographyLimit, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: questionFocusSet

  /** getter for questionFocusSet - gets question focus set
   * @generated
   * @return value of the feature 
   */
  public StringList getQuestionFocusSet() {
    if (Question_Type.featOkTst && ((Question_Type)jcasType).casFeat_questionFocusSet == null)
      jcasType.jcas.throwFeatMissing("questionFocusSet", "jeqa.types.Question");
    return (StringList)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Question_Type)jcasType).casFeatCode_questionFocusSet)));}
    
  /** setter for questionFocusSet - sets question focus set 
   * @generated
   * @param v value to set into the feature 
   */
  public void setQuestionFocusSet(StringList v) {
    if (Question_Type.featOkTst && ((Question_Type)jcasType).casFeat_questionFocusSet == null)
      jcasType.jcas.throwFeatMissing("questionFocusSet", "jeqa.types.Question");
    jcasType.ll_cas.ll_setRefValue(addr, ((Question_Type)jcasType).casFeatCode_questionFocusSet, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: lexicalAnswerTypeSet

  /** getter for lexicalAnswerTypeSet - gets lexical answer type set
   * @generated
   * @return value of the feature 
   */
  public StringList getLexicalAnswerTypeSet() {
    if (Question_Type.featOkTst && ((Question_Type)jcasType).casFeat_lexicalAnswerTypeSet == null)
      jcasType.jcas.throwFeatMissing("lexicalAnswerTypeSet", "jeqa.types.Question");
    return (StringList)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Question_Type)jcasType).casFeatCode_lexicalAnswerTypeSet)));}
    
  /** setter for lexicalAnswerTypeSet - sets lexical answer type set 
   * @generated
   * @param v value to set into the feature 
   */
  public void setLexicalAnswerTypeSet(StringList v) {
    if (Question_Type.featOkTst && ((Question_Type)jcasType).casFeat_lexicalAnswerTypeSet == null)
      jcasType.jcas.throwFeatMissing("lexicalAnswerTypeSet", "jeqa.types.Question");
    jcasType.ll_cas.ll_setRefValue(addr, ((Question_Type)jcasType).casFeatCode_lexicalAnswerTypeSet, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: semanticAnswerTypeSet

  /** getter for semanticAnswerTypeSet - gets semantic answer type set
   * @generated
   * @return value of the feature 
   */
  public StringList getSemanticAnswerTypeSet() {
    if (Question_Type.featOkTst && ((Question_Type)jcasType).casFeat_semanticAnswerTypeSet == null)
      jcasType.jcas.throwFeatMissing("semanticAnswerTypeSet", "jeqa.types.Question");
    return (StringList)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Question_Type)jcasType).casFeatCode_semanticAnswerTypeSet)));}
    
  /** setter for semanticAnswerTypeSet - sets semantic answer type set 
   * @generated
   * @param v value to set into the feature 
   */
  public void setSemanticAnswerTypeSet(StringList v) {
    if (Question_Type.featOkTst && ((Question_Type)jcasType).casFeat_semanticAnswerTypeSet == null)
      jcasType.jcas.throwFeatMissing("semanticAnswerTypeSet", "jeqa.types.Question");
    jcasType.ll_cas.ll_setRefValue(addr, ((Question_Type)jcasType).casFeatCode_semanticAnswerTypeSet, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: answerSet

  /** getter for answerSet - gets 回答のセット
   * @generated
   * @return value of the feature 
   */
  public FSList getAnswerSet() {
    if (Question_Type.featOkTst && ((Question_Type)jcasType).casFeat_answerSet == null)
      jcasType.jcas.throwFeatMissing("answerSet", "jeqa.types.Question");
    return (FSList)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Question_Type)jcasType).casFeatCode_answerSet)));}
    
  /** setter for answerSet - sets 回答のセット 
   * @generated
   * @param v value to set into the feature 
   */
  public void setAnswerSet(FSList v) {
    if (Question_Type.featOkTst && ((Question_Type)jcasType).casFeat_answerSet == null)
      jcasType.jcas.throwFeatMissing("answerSet", "jeqa.types.Question");
    jcasType.ll_cas.ll_setRefValue(addr, ((Question_Type)jcasType).casFeatCode_answerSet, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    