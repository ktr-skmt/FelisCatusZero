

/* First created by JCasGen Wed Jan 25 19:24:52 JST 2017 */
package jeqa.types.ja;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSList;
import jeqa.types.Analysis;


/** 形態素
 * Updated by JCasGen Wed Jan 25 19:24:52 JST 2017
 * XML source: src/main/resources/desc/ts/typeSystem.xml
 * @generated */
public class Morpheme extends Analysis {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Morpheme.class);
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
  protected Morpheme() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Morpheme(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Morpheme(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public Morpheme(JCas jcas, int begin, int end) {
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
  //* Feature: originalText

  /** getter for originalText - gets 元のtext
   * @generated
   * @return value of the feature 
   */
  public String getOriginalText() {
    if (Morpheme_Type.featOkTst && ((Morpheme_Type)jcasType).casFeat_originalText == null)
      jcasType.jcas.throwFeatMissing("originalText", "jeqa.types.ja.Morpheme");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Morpheme_Type)jcasType).casFeatCode_originalText);}
    
  /** setter for originalText - sets 元のtext 
   * @generated
   * @param v value to set into the feature 
   */
  public void setOriginalText(String v) {
    if (Morpheme_Type.featOkTst && ((Morpheme_Type)jcasType).casFeat_originalText == null)
      jcasType.jcas.throwFeatMissing("originalText", "jeqa.types.ja.Morpheme");
    jcasType.ll_cas.ll_setStringValue(addr, ((Morpheme_Type)jcasType).casFeatCode_originalText, v);}    
   
    
  //*--------------*
  //* Feature: text

  /** getter for text - gets 活用を戻したtext
   * @generated
   * @return value of the feature 
   */
  public String getText() {
    if (Morpheme_Type.featOkTst && ((Morpheme_Type)jcasType).casFeat_text == null)
      jcasType.jcas.throwFeatMissing("text", "jeqa.types.ja.Morpheme");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Morpheme_Type)jcasType).casFeatCode_text);}
    
  /** setter for text - sets 活用を戻したtext 
   * @generated
   * @param v value to set into the feature 
   */
  public void setText(String v) {
    if (Morpheme_Type.featOkTst && ((Morpheme_Type)jcasType).casFeat_text == null)
      jcasType.jcas.throwFeatMissing("text", "jeqa.types.ja.Morpheme");
    jcasType.ll_cas.ll_setStringValue(addr, ((Morpheme_Type)jcasType).casFeatCode_text, v);}    
   
    
  //*--------------*
  //* Feature: pos

  /** getter for pos - gets 品詞
   * @generated
   * @return value of the feature 
   */
  public String getPos() {
    if (Morpheme_Type.featOkTst && ((Morpheme_Type)jcasType).casFeat_pos == null)
      jcasType.jcas.throwFeatMissing("pos", "jeqa.types.ja.Morpheme");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Morpheme_Type)jcasType).casFeatCode_pos);}
    
  /** setter for pos - sets 品詞 
   * @generated
   * @param v value to set into the feature 
   */
  public void setPos(String v) {
    if (Morpheme_Type.featOkTst && ((Morpheme_Type)jcasType).casFeat_pos == null)
      jcasType.jcas.throwFeatMissing("pos", "jeqa.types.ja.Morpheme");
    jcasType.ll_cas.ll_setStringValue(addr, ((Morpheme_Type)jcasType).casFeatCode_pos, v);}    
   
    
  //*--------------*
  //* Feature: wordEmbeddingList

  /** getter for wordEmbeddingList - gets word vector list
   * @generated
   * @return value of the feature 
   */
  public FSList getWordEmbeddingList() {
    if (Morpheme_Type.featOkTst && ((Morpheme_Type)jcasType).casFeat_wordEmbeddingList == null)
      jcasType.jcas.throwFeatMissing("wordEmbeddingList", "jeqa.types.ja.Morpheme");
    return (FSList)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Morpheme_Type)jcasType).casFeatCode_wordEmbeddingList)));}
    
  /** setter for wordEmbeddingList - sets word vector list 
   * @generated
   * @param v value to set into the feature 
   */
  public void setWordEmbeddingList(FSList v) {
    if (Morpheme_Type.featOkTst && ((Morpheme_Type)jcasType).casFeat_wordEmbeddingList == null)
      jcasType.jcas.throwFeatMissing("wordEmbeddingList", "jeqa.types.ja.Morpheme");
    jcasType.ll_cas.ll_setRefValue(addr, ((Morpheme_Type)jcasType).casFeatCode_wordEmbeddingList, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: semanticType

  /** getter for semanticType - gets semantic type
   * @generated
   * @return value of the feature 
   */
  public SemanticType getSemanticType() {
    if (Morpheme_Type.featOkTst && ((Morpheme_Type)jcasType).casFeat_semanticType == null)
      jcasType.jcas.throwFeatMissing("semanticType", "jeqa.types.ja.Morpheme");
    return (SemanticType)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Morpheme_Type)jcasType).casFeatCode_semanticType)));}
    
  /** setter for semanticType - sets semantic type 
   * @generated
   * @param v value to set into the feature 
   */
  public void setSemanticType(SemanticType v) {
    if (Morpheme_Type.featOkTst && ((Morpheme_Type)jcasType).casFeat_semanticType == null)
      jcasType.jcas.throwFeatMissing("semanticType", "jeqa.types.ja.Morpheme");
    jcasType.ll_cas.ll_setRefValue(addr, ((Morpheme_Type)jcasType).casFeatCode_semanticType, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: isTerm

  /** getter for isTerm - gets is term
   * @generated
   * @return value of the feature 
   */
  public boolean getIsTerm() {
    if (Morpheme_Type.featOkTst && ((Morpheme_Type)jcasType).casFeat_isTerm == null)
      jcasType.jcas.throwFeatMissing("isTerm", "jeqa.types.ja.Morpheme");
    return jcasType.ll_cas.ll_getBooleanValue(addr, ((Morpheme_Type)jcasType).casFeatCode_isTerm);}
    
  /** setter for isTerm - sets is term 
   * @generated
   * @param v value to set into the feature 
   */
  public void setIsTerm(boolean v) {
    if (Morpheme_Type.featOkTst && ((Morpheme_Type)jcasType).casFeat_isTerm == null)
      jcasType.jcas.throwFeatMissing("isTerm", "jeqa.types.ja.Morpheme");
    jcasType.ll_cas.ll_setBooleanValue(addr, ((Morpheme_Type)jcasType).casFeatCode_isTerm, v);}    
  }

    