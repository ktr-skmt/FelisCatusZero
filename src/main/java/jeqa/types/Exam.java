

/* First created by JCasGen Wed Jan 25 19:24:52 JST 2017 */
package jeqa.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.tcas.Annotation;


/** 試験問題
 * Updated by JCasGen Wed Jan 25 19:24:52 JST 2017
 * XML source: src/main/resources/desc/ts/typeSystem.xml
 * @generated */
public class Exam extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Exam.class);
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
  protected Exam() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Exam(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Exam(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public Exam(JCas jcas, int begin, int end) {
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
  //* Feature: questionSet

  /** getter for questionSet - gets Examが出題するQuestionのセット
   * @generated
   * @return value of the feature 
   */
  public FSArray getQuestionSet() {
    if (Exam_Type.featOkTst && ((Exam_Type)jcasType).casFeat_questionSet == null)
      jcasType.jcas.throwFeatMissing("questionSet", "jeqa.types.Exam");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Exam_Type)jcasType).casFeatCode_questionSet)));}
    
  /** setter for questionSet - sets Examが出題するQuestionのセット 
   * @generated
   * @param v value to set into the feature 
   */
  public void setQuestionSet(FSArray v) {
    if (Exam_Type.featOkTst && ((Exam_Type)jcasType).casFeat_questionSet == null)
      jcasType.jcas.throwFeatMissing("questionSet", "jeqa.types.Exam");
    jcasType.ll_cas.ll_setRefValue(addr, ((Exam_Type)jcasType).casFeatCode_questionSet, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for questionSet - gets an indexed value - Examが出題するQuestionのセット
   * @generated
   * @param i index in the array to get
   * @return value of the element at index i 
   */
  public Question getQuestionSet(int i) {
    if (Exam_Type.featOkTst && ((Exam_Type)jcasType).casFeat_questionSet == null)
      jcasType.jcas.throwFeatMissing("questionSet", "jeqa.types.Exam");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((Exam_Type)jcasType).casFeatCode_questionSet), i);
    return (Question)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((Exam_Type)jcasType).casFeatCode_questionSet), i)));}

  /** indexed setter for questionSet - sets an indexed value - Examが出題するQuestionのセット
   * @generated
   * @param i index in the array to set
   * @param v value to set into the array 
   */
  public void setQuestionSet(int i, Question v) { 
    if (Exam_Type.featOkTst && ((Exam_Type)jcasType).casFeat_questionSet == null)
      jcasType.jcas.throwFeatMissing("questionSet", "jeqa.types.Exam");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((Exam_Type)jcasType).casFeatCode_questionSet), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((Exam_Type)jcasType).casFeatCode_questionSet), i, jcasType.ll_cas.ll_getFSRef(v));}
   
    
  //*--------------*
  //* Feature: label

  /** getter for label - gets Examにつけられたユニークなラベル(実際にはExamのファイル名が入る)
   * @generated
   * @return value of the feature 
   */
  public String getLabel() {
    if (Exam_Type.featOkTst && ((Exam_Type)jcasType).casFeat_label == null)
      jcasType.jcas.throwFeatMissing("label", "jeqa.types.Exam");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Exam_Type)jcasType).casFeatCode_label);}
    
  /** setter for label - sets Examにつけられたユニークなラベル(実際にはExamのファイル名が入る) 
   * @generated
   * @param v value to set into the feature 
   */
  public void setLabel(String v) {
    if (Exam_Type.featOkTst && ((Exam_Type)jcasType).casFeat_label == null)
      jcasType.jcas.throwFeatMissing("label", "jeqa.types.Exam");
    jcasType.ll_cas.ll_setStringValue(addr, ((Exam_Type)jcasType).casFeatCode_label, v);}    
   
    
  //*--------------*
  //* Feature: dir

  /** getter for dir - gets Examのファイルが保持されているディレクトリへのパス
   * @generated
   * @return value of the feature 
   */
  public String getDir() {
    if (Exam_Type.featOkTst && ((Exam_Type)jcasType).casFeat_dir == null)
      jcasType.jcas.throwFeatMissing("dir", "jeqa.types.Exam");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Exam_Type)jcasType).casFeatCode_dir);}
    
  /** setter for dir - sets Examのファイルが保持されているディレクトリへのパス 
   * @generated
   * @param v value to set into the feature 
   */
  public void setDir(String v) {
    if (Exam_Type.featOkTst && ((Exam_Type)jcasType).casFeat_dir == null)
      jcasType.jcas.throwFeatMissing("dir", "jeqa.types.Exam");
    jcasType.ll_cas.ll_setStringValue(addr, ((Exam_Type)jcasType).casFeatCode_dir, v);}    
   
    
  //*--------------*
  //* Feature: lang

  /** getter for lang - gets 言語
   * @generated
   * @return value of the feature 
   */
  public String getLang() {
    if (Exam_Type.featOkTst && ((Exam_Type)jcasType).casFeat_lang == null)
      jcasType.jcas.throwFeatMissing("lang", "jeqa.types.Exam");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Exam_Type)jcasType).casFeatCode_lang);}
    
  /** setter for lang - sets 言語 
   * @generated
   * @param v value to set into the feature 
   */
  public void setLang(String v) {
    if (Exam_Type.featOkTst && ((Exam_Type)jcasType).casFeat_lang == null)
      jcasType.jcas.throwFeatMissing("lang", "jeqa.types.Exam");
    jcasType.ll_cas.ll_setStringValue(addr, ((Exam_Type)jcasType).casFeatCode_lang, v);}    
  }

    