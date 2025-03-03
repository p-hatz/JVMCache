import java.util.Properties;
import java.io.InputStream;
import com.boomi.execution.ExecutionUtil;
import com.boomi.Cache;

log = ExecutionUtil.getBaseLogger();

def cache = new Cache();

_keyDel = ExecutionUtil.getDynamicProcessProperty("dppKeyDel");
cache.delete(_keyDel);

for( int i = 0; i < dataContext.getDataCount(); i++ ) {
    InputStream is = dataContext.getStream(i);
    Properties props = dataContext.getProperties(i);

    dataContext.storeStream(is, props);
}
