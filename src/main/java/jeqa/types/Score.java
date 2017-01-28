

/* First created by JCasGen Wed Jan 25 19:24:52 JST 2017 */
package jeqa.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.TOP;


/** スコア
 * Updated by JCasGen Wed Jan 25 19:24:52 JST 2017
 * XML source: src/main/resources/desc/ts/typeSystem.xml
 * @generated */
public class Score extends TOP {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Score.class);
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
  protected Score() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Score(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Score(JCas jcas) {
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
  //* Feature: score

  /** getter for score - gets スコア
   * @generated
   * @return value of the feature 
   */
  public double getScore() {
    if (Score_Type.featOkTst && ((Score_Type)jcasType).casFeat_score == null)
      jcasType.jcas.throwFeatMissing("score", "jeqa.types.Score");
    return jcasType.ll_cas.ll_getDoubleValue(addr, ((Score_Type)jcasType).casFeatCode_score);}
    
  /** setter for score - sets スコア 
   * @generated
   * @param v value to set into the feature 
   */
  public void setScore(double v) {
    if (Score_Type.featOkTst && ((Score_Type)jcasType).casFeat_score == null)
      jcasType.jcas.throwFeatMissing("score", "jeqa.types.Score");
    jcasType.ll_cas.ll_setDoubleValue(addr, ((Score_Type)jcasType).casFeatCode_score, v);}    
   
    
  //*--------------*
  //* Feature: scorer

  /** getter for scorer - gets スコアラ
   * @generated
   * @return value of the feature 
   */
  public String getScorer() {
    if (Score_Type.featOkTst && ((Score_Type)jcasType).casFeat_scorer == null)
      jcasType.jcas.throwFeatMissing("scorer", "jeqa.types.Score");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Score_Type)jcasType).casFeatCode_scorer);}
    
  /** setter for scorer - sets スコアラ 
   * @generated
   * @param v value to set into the feature 
   */
  public void setScorer(String v) {
    if (Score_Type.featOkTst && ((Score_Type)jcasType).casFeat_scorer == null)
      jcasType.jcas.throwFeatMissing("scorer", "jeqa.types.Score");
    jcasType.ll_cas.ll_setStringValue(addr, ((Score_Type)jcasType).casFeatCode_scorer, v);}    
  }

    