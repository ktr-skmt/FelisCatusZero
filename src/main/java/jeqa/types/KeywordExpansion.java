

/* First created by JCasGen Wed Jan 25 19:24:52 JST 2017 */
package jeqa.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.TOP;


/** keyword expansion
 * Updated by JCasGen Wed Jan 25 19:24:52 JST 2017
 * XML source: src/main/resources/desc/ts/typeSystem.xml
 * @generated */
public class KeywordExpansion extends TOP {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(KeywordExpansion.class);
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
  protected KeywordExpansion() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public KeywordExpansion(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public KeywordExpansion(JCas jcas) {
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
  //* Feature: text

  /** getter for text - gets text
   * @generated
   * @return value of the feature 
   */
  public String getText() {
    if (KeywordExpansion_Type.featOkTst && ((KeywordExpansion_Type)jcasType).casFeat_text == null)
      jcasType.jcas.throwFeatMissing("text", "jeqa.types.KeywordExpansion");
    return jcasType.ll_cas.ll_getStringValue(addr, ((KeywordExpansion_Type)jcasType).casFeatCode_text);}
    
  /** setter for text - sets text 
   * @generated
   * @param v value to set into the feature 
   */
  public void setText(String v) {
    if (KeywordExpansion_Type.featOkTst && ((KeywordExpansion_Type)jcasType).casFeat_text == null)
      jcasType.jcas.throwFeatMissing("text", "jeqa.types.KeywordExpansion");
    jcasType.ll_cas.ll_setStringValue(addr, ((KeywordExpansion_Type)jcasType).casFeatCode_text, v);}    
   
    
  //*--------------*
  //* Feature: isMandatory

  /** getter for isMandatory - gets is mandatory
   * @generated
   * @return value of the feature 
   */
  public boolean getIsMandatory() {
    if (KeywordExpansion_Type.featOkTst && ((KeywordExpansion_Type)jcasType).casFeat_isMandatory == null)
      jcasType.jcas.throwFeatMissing("isMandatory", "jeqa.types.KeywordExpansion");
    return jcasType.ll_cas.ll_getBooleanValue(addr, ((KeywordExpansion_Type)jcasType).casFeatCode_isMandatory);}
    
  /** setter for isMandatory - sets is mandatory 
   * @generated
   * @param v value to set into the feature 
   */
  public void setIsMandatory(boolean v) {
    if (KeywordExpansion_Type.featOkTst && ((KeywordExpansion_Type)jcasType).casFeat_isMandatory == null)
      jcasType.jcas.throwFeatMissing("isMandatory", "jeqa.types.KeywordExpansion");
    jcasType.ll_cas.ll_setBooleanValue(addr, ((KeywordExpansion_Type)jcasType).casFeatCode_isMandatory, v);}    
  }

    