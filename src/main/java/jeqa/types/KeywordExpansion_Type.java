
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

/** keyword expansion
 * Updated by JCasGen Wed Jan 25 19:24:52 JST 2017
 * @generated */
public class KeywordExpansion_Type extends TOP_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (KeywordExpansion_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = KeywordExpansion_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new KeywordExpansion(addr, KeywordExpansion_Type.this);
  			   KeywordExpansion_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new KeywordExpansion(addr, KeywordExpansion_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = KeywordExpansion.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("jeqa.types.KeywordExpansion");
 
  /** @generated */
  final Feature casFeat_text;
  /** @generated */
  final int     casFeatCode_text;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getText(int addr) {
        if (featOkTst && casFeat_text == null)
      jcas.throwFeatMissing("text", "jeqa.types.KeywordExpansion");
    return ll_cas.ll_getStringValue(addr, casFeatCode_text);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setText(int addr, String v) {
        if (featOkTst && casFeat_text == null)
      jcas.throwFeatMissing("text", "jeqa.types.KeywordExpansion");
    ll_cas.ll_setStringValue(addr, casFeatCode_text, v);}
    
  
 
  /** @generated */
  final Feature casFeat_isMandatory;
  /** @generated */
  final int     casFeatCode_isMandatory;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public boolean getIsMandatory(int addr) {
        if (featOkTst && casFeat_isMandatory == null)
      jcas.throwFeatMissing("isMandatory", "jeqa.types.KeywordExpansion");
    return ll_cas.ll_getBooleanValue(addr, casFeatCode_isMandatory);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setIsMandatory(int addr, boolean v) {
        if (featOkTst && casFeat_isMandatory == null)
      jcas.throwFeatMissing("isMandatory", "jeqa.types.KeywordExpansion");
    ll_cas.ll_setBooleanValue(addr, casFeatCode_isMandatory, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public KeywordExpansion_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_text = jcas.getRequiredFeatureDE(casType, "text", "uima.cas.String", featOkTst);
    casFeatCode_text  = (null == casFeat_text) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_text).getCode();

 
    casFeat_isMandatory = jcas.getRequiredFeatureDE(casType, "isMandatory", "uima.cas.Boolean", featOkTst);
    casFeatCode_isMandatory  = (null == casFeat_isMandatory) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_isMandatory).getCode();

  }
}



    