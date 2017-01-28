

/* First created by JCasGen Wed Jan 25 19:24:52 JST 2017 */
package jeqa.types.ja;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSList;
import jeqa.types.Analysis;


/** MecabやJumanの結果を保持する
 * Updated by JCasGen Wed Jan 25 19:24:52 JST 2017
 * XML source: src/main/resources/desc/ts/typeSystem.xml
 * @generated */
public class MorphemeAnalysis extends Analysis {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(MorphemeAnalysis.class);
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
  protected MorphemeAnalysis() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public MorphemeAnalysis(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public MorphemeAnalysis(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public MorphemeAnalysis(JCas jcas, int begin, int end) {
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
  //* Feature: analyzer

  /** getter for analyzer - gets analyzer name
   * @generated
   * @return value of the feature 
   */
  public String getAnalyzer() {
    if (MorphemeAnalysis_Type.featOkTst && ((MorphemeAnalysis_Type)jcasType).casFeat_analyzer == null)
      jcasType.jcas.throwFeatMissing("analyzer", "jeqa.types.ja.MorphemeAnalysis");
    return jcasType.ll_cas.ll_getStringValue(addr, ((MorphemeAnalysis_Type)jcasType).casFeatCode_analyzer);}
    
  /** setter for analyzer - sets analyzer name 
   * @generated
   * @param v value to set into the feature 
   */
  public void setAnalyzer(String v) {
    if (MorphemeAnalysis_Type.featOkTst && ((MorphemeAnalysis_Type)jcasType).casFeat_analyzer == null)
      jcasType.jcas.throwFeatMissing("analyzer", "jeqa.types.ja.MorphemeAnalysis");
    jcasType.ll_cas.ll_setStringValue(addr, ((MorphemeAnalysis_Type)jcasType).casFeatCode_analyzer, v);}    
   
    
  //*--------------*
  //* Feature: morphemeList

  /** getter for morphemeList - gets 形態素のリスト
   * @generated
   * @return value of the feature 
   */
  public FSList getMorphemeList() {
    if (MorphemeAnalysis_Type.featOkTst && ((MorphemeAnalysis_Type)jcasType).casFeat_morphemeList == null)
      jcasType.jcas.throwFeatMissing("morphemeList", "jeqa.types.ja.MorphemeAnalysis");
    return (FSList)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((MorphemeAnalysis_Type)jcasType).casFeatCode_morphemeList)));}
    
  /** setter for morphemeList - sets 形態素のリスト 
   * @generated
   * @param v value to set into the feature 
   */
  public void setMorphemeList(FSList v) {
    if (MorphemeAnalysis_Type.featOkTst && ((MorphemeAnalysis_Type)jcasType).casFeat_morphemeList == null)
      jcasType.jcas.throwFeatMissing("morphemeList", "jeqa.types.ja.MorphemeAnalysis");
    jcasType.ll_cas.ll_setRefValue(addr, ((MorphemeAnalysis_Type)jcasType).casFeatCode_morphemeList, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    