
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

/** Single Keyword type Query
 * Updated by JCasGen Wed Jan 25 19:24:52 JST 2017
 * @generated */
public class KeywordQuery_Type extends Query_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (KeywordQuery_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = KeywordQuery_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new KeywordQuery(addr, KeywordQuery_Type.this);
  			   KeywordQuery_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new KeywordQuery(addr, KeywordQuery_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = KeywordQuery.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("jeqa.types.KeywordQuery");
 
  /** @generated */
  final Feature casFeat_keyword;
  /** @generated */
  final int     casFeatCode_keyword;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getKeyword(int addr) {
        if (featOkTst && casFeat_keyword == null)
      jcas.throwFeatMissing("keyword", "jeqa.types.KeywordQuery");
    return ll_cas.ll_getRefValue(addr, casFeatCode_keyword);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setKeyword(int addr, int v) {
        if (featOkTst && casFeat_keyword == null)
      jcas.throwFeatMissing("keyword", "jeqa.types.KeywordQuery");
    ll_cas.ll_setRefValue(addr, casFeatCode_keyword, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public KeywordQuery_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_keyword = jcas.getRequiredFeatureDE(casType, "keyword", "jeqa.types.Keyword", featOkTst);
    casFeatCode_keyword  = (null == casFeat_keyword) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_keyword).getCode();

  }
}



    