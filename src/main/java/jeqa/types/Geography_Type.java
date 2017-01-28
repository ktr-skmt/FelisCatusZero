
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

/** 地域
 * Updated by JCasGen Wed Jan 25 19:24:52 JST 2017
 * @generated */
public class Geography_Type extends TOP_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Geography_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Geography_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Geography(addr, Geography_Type.this);
  			   Geography_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Geography(addr, Geography_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Geography.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("jeqa.types.Geography");
 
  /** @generated */
  final Feature casFeat_termList;
  /** @generated */
  final int     casFeatCode_termList;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getTermList(int addr) {
        if (featOkTst && casFeat_termList == null)
      jcas.throwFeatMissing("termList", "jeqa.types.Geography");
    return ll_cas.ll_getRefValue(addr, casFeatCode_termList);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setTermList(int addr, int v) {
        if (featOkTst && casFeat_termList == null)
      jcas.throwFeatMissing("termList", "jeqa.types.Geography");
    ll_cas.ll_setRefValue(addr, casFeatCode_termList, v);}
    
  
 
  /** @generated */
  final Feature casFeat_area;
  /** @generated */
  final int     casFeatCode_area;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getArea(int addr) {
        if (featOkTst && casFeat_area == null)
      jcas.throwFeatMissing("area", "jeqa.types.Geography");
    return ll_cas.ll_getRefValue(addr, casFeatCode_area);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setArea(int addr, int v) {
        if (featOkTst && casFeat_area == null)
      jcas.throwFeatMissing("area", "jeqa.types.Geography");
    ll_cas.ll_setRefValue(addr, casFeatCode_area, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public Geography_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_termList = jcas.getRequiredFeatureDE(casType, "termList", "uima.cas.StringList", featOkTst);
    casFeatCode_termList  = (null == casFeat_termList) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_termList).getCode();

 
    casFeat_area = jcas.getRequiredFeatureDE(casType, "area", "uima.cas.StringList", featOkTst);
    casFeatCode_area  = (null == casFeat_area) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_area).getCode();

  }
}



    