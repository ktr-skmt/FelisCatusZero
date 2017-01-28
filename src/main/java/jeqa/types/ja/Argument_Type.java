
/* First created by JCasGen Wed Jan 25 19:24:52 JST 2017 */
package jeqa.types.ja;

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

/** 項
 * Updated by JCasGen Wed Jan 25 19:24:52 JST 2017
 * @generated */
public class Argument_Type extends TOP_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Argument_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Argument_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Argument(addr, Argument_Type.this);
  			   Argument_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Argument(addr, Argument_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Argument.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("jeqa.types.ja.Argument");
 
  /** @generated */
  final Feature casFeat_case;
  /** @generated */
  final int     casFeatCode_case;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getCase(int addr) {
        if (featOkTst && casFeat_case == null)
      jcas.throwFeatMissing("case", "jeqa.types.ja.Argument");
    return ll_cas.ll_getStringValue(addr, casFeatCode_case);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setCase(int addr, String v) {
        if (featOkTst && casFeat_case == null)
      jcas.throwFeatMissing("case", "jeqa.types.ja.Argument");
    ll_cas.ll_setStringValue(addr, casFeatCode_case, v);}
    
  
 
  /** @generated */
  final Feature casFeat_bunsetsu;
  /** @generated */
  final int     casFeatCode_bunsetsu;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getBunsetsu(int addr) {
        if (featOkTst && casFeat_bunsetsu == null)
      jcas.throwFeatMissing("bunsetsu", "jeqa.types.ja.Argument");
    return ll_cas.ll_getRefValue(addr, casFeatCode_bunsetsu);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setBunsetsu(int addr, int v) {
        if (featOkTst && casFeat_bunsetsu == null)
      jcas.throwFeatMissing("bunsetsu", "jeqa.types.ja.Argument");
    ll_cas.ll_setRefValue(addr, casFeatCode_bunsetsu, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public Argument_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_case = jcas.getRequiredFeatureDE(casType, "case", "uima.cas.String", featOkTst);
    casFeatCode_case  = (null == casFeat_case) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_case).getCode();

 
    casFeat_bunsetsu = jcas.getRequiredFeatureDE(casType, "bunsetsu", "jeqa.types.ja.Bunsetsu", featOkTst);
    casFeatCode_bunsetsu  = (null == casFeat_bunsetsu) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_bunsetsu).getCode();

  }
}



    