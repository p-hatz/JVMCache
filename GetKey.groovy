import java.util.Properties;
import java.io.InputStream;
import com.boomi.execution.ExecutionUtil;
import com.boomi.Cache;

def cache = new Cache();

_key = ExecutionUtil.getDynamicProcessProperty("dppKey");
String _keyVal = Cache.get(_key);

ExecutionUtil.setDynamicProcessProperty("dppCacheVal", _keyVal, false);

for( int i = 0; i < dataContext.getDataCount(); i++ ) {
    InputStream is = dataContext.getStream(i);
    Properties props = dataContext.getProperties(i);

    dataContext.storeStream(is, props);
}
