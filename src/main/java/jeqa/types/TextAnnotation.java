

/* First created by JCasGen Wed Jan 25 19:24:52 JST 2017 */
package jeqa.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.cas.FSList;
import org.apache.uima.jcas.cas.StringList;
import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Wed Jan 25 19:24:52 JST 2017
 * XML source: src/main/resources/desc/ts/typeSystem.xml
 * @generated */
public class TextAnnotation extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(TextAnnotation.class);
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
  protected TextAnnotation() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public TextAnnotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public TextAnnotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public TextAnnotation(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
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
  //* Feature: id

  /** getter for id - gets Textが持っているID
   * @generated
   * @return value of the feature 
   */
  public long getId() {
    if (TextAnnotation_Type.featOkTst && ((TextAnnotation_Type)jcasType).casFeat_id == null)
      jcasType.jcas.throwFeatMissing("id", "jeqa.types.TextAnnotation");
    return jcasType.ll_cas.ll_getLongValue(addr, ((TextAnnotation_Type)jcasType).casFeatCode_id);}
    
  /** setter for id - sets Textが持っているID 
   * @generated
   * @param v value to set into the feature 
   */
  public void setId(long v) {
    if (TextAnnotation_Type.featOkTst && ((TextAnnotation_Type)jcasType).casFeat_id == null)
      jcasType.jcas.throwFeatMissing("id", "jeqa.types.TextAnnotation");
    jcasType.ll_cas.ll_setLongValue(addr, ((TextAnnotation_Type)jcasType).casFeatCode_id, v);}    
   
    
  //*--------------*
  //* Feature: text

  /** getter for text - gets Textが持っているテキスト
   * @generated
   * @return value of the feature 
   */
  public String getText() {
    if (TextAnnotation_Type.featOkTst && ((TextAnnotation_Type)jcasType).casFeat_text == null)
      jcasType.jcas.throwFeatMissing("text", "jeqa.types.TextAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((TextAnnotation_Type)jcasType).casFeatCode_text);}
    
  /** setter for text - sets Textが持っているテキスト 
   * @generated
   * @param v value to set into the feature 
   */
  public void setText(String v) {
    if (TextAnnotation_Type.featOkTst && ((TextAnnotation_Type)jcasType).casFeat_text == null)
      jcasType.jcas.throwFeatMissing("text", "jeqa.types.TextAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((TextAnnotation_Type)jcasType).casFeatCode_text, v);}    
   
    
  //*--------------*
  //* Feature: docno

  /** getter for docno - gets DOCNO
   * @generated
   * @return value of the feature 
   */
  public String getDocno() {
    if (TextAnnotation_Type.featOkTst && ((TextAnnotation_Type)jcasType).casFeat_docno == null)
      jcasType.jcas.throwFeatMissing("docno", "jeqa.types.TextAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((TextAnnotation_Type)jcasType).casFeatCode_docno);}
    
  /** setter for docno - sets DOCNO 
   * @generated
   * @param v value to set into the feature 
   */
  public void setDocno(String v) {
    if (TextAnnotation_Type.featOkTst && ((TextAnnotation_Type)jcasType).casFeat_docno == null)
      jcasType.jcas.throwFeatMissing("docno", "jeqa.types.TextAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((TextAnnotation_Type)jcasType).casFeatCode_docno, v);}    
   
    
  //*--------------*
  //* Feature: title

  /** getter for title - gets title
   * @generated
   * @return value of the feature 
   */
  public String getTitle() {
    if (TextAnnotation_Type.featOkTst && ((TextAnnotation_Type)jcasType).casFeat_title == null)
      jcasType.jcas.throwFeatMissing("title", "jeqa.types.TextAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((TextAnnotation_Type)jcasType).casFeatCode_title);}
    
  /** setter for title - sets title 
   * @generated
   * @param v value to set into the feature 
   */
  public void setTitle(String v) {
    if (TextAnnotation_Type.featOkTst && ((TextAnnotation_Type)jcasType).casFeat_title == null)
      jcasType.jcas.throwFeatMissing("title", "jeqa.types.TextAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((TextAnnotation_Type)jcasType).casFeatCode_title, v);}    
   
    
  //*--------------*
  //* Feature: scoreList

  /** getter for scoreList - gets スコアのリスト。インデックスがスコアラと対応する
   * @generated
   * @return value of the feature 
   */
  public FSArray getScoreList() {
    if (TextAnnotation_Type.featOkTst && ((TextAnnotation_Type)jcasType).casFeat_scoreList == null)
      jcasType.jcas.throwFeatMissing("scoreList", "jeqa.types.TextAnnotation");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((TextAnnotation_Type)jcasType).casFeatCode_scoreList)));}
    
  /** setter for scoreList - sets スコアのリスト。インデックスがスコアラと対応する 
   * @generated
   * @param v value to set into the feature 
   */
  public void setScoreList(FSArray v) {
    if (TextAnnotation_Type.featOkTst && ((TextAnnotation_Type)jcasType).casFeat_scoreList == null)
      jcasType.jcas.throwFeatMissing("scoreList", "jeqa.types.TextAnnotation");
    jcasType.ll_cas.ll_setRefValue(addr, ((TextAnnotation_Type)jcasType).casFeatCode_scoreList, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for scoreList - gets an indexed value - スコアのリスト。インデックスがスコアラと対応する
   * @generated
   * @param i index in the array to get
   * @return value of the element at index i 
   */
  public Score getScoreList(int i) {
    if (TextAnnotation_Type.featOkTst && ((TextAnnotation_Type)jcasType).casFeat_scoreList == null)
      jcasType.jcas.throwFeatMissing("scoreList", "jeqa.types.TextAnnotation");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((TextAnnotation_Type)jcasType).casFeatCode_scoreList), i);
    return (Score)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((TextAnnotation_Type)jcasType).casFeatCode_scoreList), i)));}

  /** indexed setter for scoreList - sets an indexed value - スコアのリスト。インデックスがスコアラと対応する
   * @generated
   * @param i index in the array to set
   * @param v value to set into the array 
   */
  public void setScoreList(int i, Score v) { 
    if (TextAnnotation_Type.featOkTst && ((TextAnnotation_Type)jcasType).casFeat_scoreList == null)
      jcasType.jcas.throwFeatMissing("scoreList", "jeqa.types.TextAnnotation");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((TextAnnotation_Type)jcasType).casFeatCode_scoreList), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((TextAnnotation_Type)jcasType).casFeatCode_scoreList), i, jcasType.ll_cas.ll_getFSRef(v));}
   
    
  //*--------------*
  //* Feature: morphemeList

  /** getter for morphemeList - gets 形態素のリスト
   * @generated
   * @return value of the feature 
   */
  public FSList getMorphemeList() {
    if (TextAnnotation_Type.featOkTst && ((TextAnnotation_Type)jcasType).casFeat_morphemeList == null)
      jcasType.jcas.throwFeatMissing("morphemeList", "jeqa.types.TextAnnotation");
    return (FSList)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((TextAnnotation_Type)jcasType).casFeatCode_morphemeList)));}
    
  /** setter for morphemeList - sets 形態素のリスト 
   * @generated
   * @param v value to set into the feature 
   */
  public void setMorphemeList(FSList v) {
    if (TextAnnotation_Type.featOkTst && ((TextAnnotation_Type)jcasType).casFeat_morphemeList == null)
      jcasType.jcas.throwFeatMissing("morphemeList", "jeqa.types.TextAnnotation");
    jcasType.ll_cas.ll_setRefValue(addr, ((TextAnnotation_Type)jcasType).casFeatCode_morphemeList, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: contentWordList

  /** getter for contentWordList - gets 内容語リスト
   * @generated
   * @return value of the feature 
   */
  public StringList getContentWordList() {
    if (TextAnnotation_Type.featOkTst && ((TextAnnotation_Type)jcasType).casFeat_contentWordList == null)
      jcasType.jcas.throwFeatMissing("contentWordList", "jeqa.types.TextAnnotation");
    return (StringList)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((TextAnnotation_Type)jcasType).casFeatCode_contentWordList)));}
    
  /** setter for contentWordList - sets 内容語リスト 
   * @generated
   * @param v value to set into the feature 
   */
  public void setContentWordList(StringList v) {
    if (TextAnnotation_Type.featOkTst && ((TextAnnotation_Type)jcasType).casFeat_contentWordList == null)
      jcasType.jcas.throwFeatMissing("contentWordList", "jeqa.types.TextAnnotation");
    jcasType.ll_cas.ll_setRefValue(addr, ((TextAnnotation_Type)jcasType).casFeatCode_contentWordList, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: beginTime

  /** getter for beginTime - gets 開始時間
   * @generated
   * @return value of the feature 
   */
  public Time getBeginTime() {
    if (TextAnnotation_Type.featOkTst && ((TextAnnotation_Type)jcasType).casFeat_beginTime == null)
      jcasType.jcas.throwFeatMissing("beginTime", "jeqa.types.TextAnnotation");
    return (Time)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((TextAnnotation_Type)jcasType).casFeatCode_beginTime)));}
    
  /** setter for beginTime - sets 開始時間 
   * @generated
   * @param v value to set into the feature 
   */
  public void setBeginTime(Time v) {
    if (TextAnnotation_Type.featOkTst && ((TextAnnotation_Type)jcasType).casFeat_beginTime == null)
      jcasType.jcas.throwFeatMissing("beginTime", "jeqa.types.TextAnnotation");
    jcasType.ll_cas.ll_setRefValue(addr, ((TextAnnotation_Type)jcasType).casFeatCode_beginTime, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: endTime

  /** getter for endTime - gets 終了時間
   * @generated
   * @return value of the feature 
   */
  public Time getEndTime() {
    if (TextAnnotation_Type.featOkTst && ((TextAnnotation_Type)jcasType).casFeat_endTime == null)
      jcasType.jcas.throwFeatMissing("endTime", "jeqa.types.TextAnnotation");
    return (Time)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((TextAnnotation_Type)jcasType).casFeatCode_endTime)));}
    
  /** setter for endTime - sets 終了時間 
   * @generated
   * @param v value to set into the feature 
   */
  public void setEndTime(Time v) {
    if (TextAnnotation_Type.featOkTst && ((TextAnnotation_Type)jcasType).casFeat_endTime == null)
      jcasType.jcas.throwFeatMissing("endTime", "jeqa.types.TextAnnotation");
    jcasType.ll_cas.ll_setRefValue(addr, ((TextAnnotation_Type)jcasType).casFeatCode_endTime, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: geography

  /** getter for geography - gets geography
   * @generated
   * @return value of the feature 
   */
  public Geography getGeography() {
    if (TextAnnotation_Type.featOkTst && ((TextAnnotation_Type)jcasType).casFeat_geography == null)
      jcasType.jcas.throwFeatMissing("geography", "jeqa.types.TextAnnotation");
    return (Geography)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((TextAnnotation_Type)jcasType).casFeatCode_geography)));}
    
  /** setter for geography - sets geography 
   * @generated
   * @param v value to set into the feature 
   */
  public void setGeography(Geography v) {
    if (TextAnnotation_Type.featOkTst && ((TextAnnotation_Type)jcasType).casFeat_geography == null)
      jcasType.jcas.throwFeatMissing("geography", "jeqa.types.TextAnnotation");
    jcasType.ll_cas.ll_setRefValue(addr, ((TextAnnotation_Type)jcasType).casFeatCode_geography, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: semanticTypeList

  /** getter for semanticTypeList - gets semantic type list
   * @generated
   * @return value of the feature 
   */
  public FSList getSemanticTypeList() {
    if (TextAnnotation_Type.featOkTst && ((TextAnnotation_Type)jcasType).casFeat_semanticTypeList == null)
      jcasType.jcas.throwFeatMissing("semanticTypeList", "jeqa.types.TextAnnotation");
    return (FSList)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((TextAnnotation_Type)jcasType).casFeatCode_semanticTypeList)));}
    
  /** setter for semanticTypeList - sets semantic type list 
   * @generated
   * @param v value to set into the feature 
   */
  public void setSemanticTypeList(FSList v) {
    if (TextAnnotation_Type.featOkTst && ((TextAnnotation_Type)jcasType).casFeat_semanticTypeList == null)
      jcasType.jcas.throwFeatMissing("semanticTypeList", "jeqa.types.TextAnnotation");
    jcasType.ll_cas.ll_setRefValue(addr, ((TextAnnotation_Type)jcasType).casFeatCode_semanticTypeList, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    