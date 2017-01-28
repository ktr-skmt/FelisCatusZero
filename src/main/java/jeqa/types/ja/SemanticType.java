

/* First created by JCasGen Wed Jan 25 19:24:52 JST 2017 */
package jeqa.types.ja;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.TOP;
import org.apache.uima.jcas.cas.StringList;


/** semantic type
 * Updated by JCasGen Wed Jan 25 19:24:52 JST 2017
 * XML source: src/main/resources/desc/ts/typeSystem.xml
 * @generated */
public class SemanticType extends TOP {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(SemanticType.class);
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
  protected SemanticType() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public SemanticType(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public SemanticType(JCas jcas) {
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
  //* Feature: category

  /** getter for category - gets category
   * @generated
   * @return value of the feature 
   */
  public String getCategory() {
    if (SemanticType_Type.featOkTst && ((SemanticType_Type)jcasType).casFeat_category == null)
      jcasType.jcas.throwFeatMissing("category", "jeqa.types.ja.SemanticType");
    return jcasType.ll_cas.ll_getStringValue(addr, ((SemanticType_Type)jcasType).casFeatCode_category);}
    
  /** setter for category - sets category 
   * @generated
   * @param v value to set into the feature 
   */
  public void setCategory(String v) {
    if (SemanticType_Type.featOkTst && ((SemanticType_Type)jcasType).casFeat_category == null)
      jcasType.jcas.throwFeatMissing("category", "jeqa.types.ja.SemanticType");
    jcasType.ll_cas.ll_setStringValue(addr, ((SemanticType_Type)jcasType).casFeatCode_category, v);}    
   
    
  //*--------------*
  //* Feature: typeList

  /** getter for typeList - gets types
   * @generated
   * @return value of the feature 
   */
  public StringList getTypeList() {
    if (SemanticType_Type.featOkTst && ((SemanticType_Type)jcasType).casFeat_typeList == null)
      jcasType.jcas.throwFeatMissing("typeList", "jeqa.types.ja.SemanticType");
    return (StringList)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((SemanticType_Type)jcasType).casFeatCode_typeList)));}
    
  /** setter for typeList - sets types 
   * @generated
   * @param v value to set into the feature 
   */
  public void setTypeList(StringList v) {
    if (SemanticType_Type.featOkTst && ((SemanticType_Type)jcasType).casFeat_typeList == null)
      jcasType.jcas.throwFeatMissing("typeList", "jeqa.types.ja.SemanticType");
    jcasType.ll_cas.ll_setRefValue(addr, ((SemanticType_Type)jcasType).casFeatCode_typeList, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    