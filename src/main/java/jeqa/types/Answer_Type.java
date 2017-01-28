
/* First created by JCasGen Wed Jan 25 19:24:52 JST 2017 */
package jeqa.types;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.cas.TOP_Type;

/** 回答
 * Updated by JCasGen Wed Jan 25 19:24:52 JST 2017
 * @generated */
public class Answer_Type extends TOP_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Answer_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Answer_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Answer(addr, Answer_Type.this);
  			   Answer_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Answer(addr, Answer_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Answer.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("jeqa.types.Answer");
 
  /** @generated */
  final Feature casFeat_document;
  /** @generated */
  final int     casFeatCode_document;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getDocument(int addr) {
        if (featOkTst && casFeat_document == null)
      jcas.throwFeatMissing("document", "jeqa.types.Answer");
    return ll_cas.ll_getRefValue(addr, casFeatCode_document);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setDocument(int addr, int v) {
        if (featOkTst && casFeat_document == null)
      jcas.throwFeatMissing("document", "jeqa.types.Answer");
    ll_cas.ll_setRefValue(addr, casFeatCode_document, v);}
    
  
 
  /** @generated */
  final Feature casFeat_isGoldStandard;
  /** @generated */
  final int     casFeatCode_isGoldStandard;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public boolean getIsGoldStandard(int addr) {
        if (featOkTst && casFeat_isGoldStandard == null)
      jcas.throwFeatMissing("isGoldStandard", "jeqa.types.Answer");
    return ll_cas.ll_getBooleanValue(addr, casFeatCode_isGoldStandard);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setIsGoldStandard(int addr, boolean v) {
        if (featOkTst && casFeat_isGoldStandard == null)
      jcas.throwFeatMissing("isGoldStandard", "jeqa.types.Answer");
    ll_cas.ll_setBooleanValue(addr, casFeatCode_isGoldStandard, v);}
    
  
 
  /** @generated */
  final Feature casFeat_writer;
  /** @generated */
  final int     casFeatCode_writer;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getWriter(int addr) {
        if (featOkTst && casFeat_writer == null)
      jcas.throwFeatMissing("writer", "jeqa.types.Answer");
    return ll_cas.ll_getStringValue(addr, casFeatCode_writer);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setWriter(int addr, String v) {
        if (featOkTst && casFeat_writer == null)
      jcas.throwFeatMissing("writer", "jeqa.types.Answer");
    ll_cas.ll_setStringValue(addr, casFeatCode_writer, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public Answer_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_document = jcas.getRequiredFeatureDE(casType, "document", "jeqa.types.Document", featOkTst);
    casFeatCode_document  = (null == casFeat_document) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_document).getCode();

 
    casFeat_isGoldStandard = jcas.getRequiredFeatureDE(casType, "isGoldStandard", "uima.cas.Boolean", featOkTst);
    casFeatCode_isGoldStandard  = (null == casFeat_isGoldStandard) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_isGoldStandard).getCode();

 
    casFeat_writer = jcas.getRequiredFeatureDE(casType, "writer", "uima.cas.String", featOkTst);
    casFeatCode_writer  = (null == casFeat_writer) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_writer).getCode();

  }
}



    