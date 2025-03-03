import java.util.Properties;
import java.io.InputStream;
import com.boomi.execution.ExecutionUtil;
import com.boomi.Cache;

def cache = new Cache();

inData = ExecutionUtil.getDynamicProcessProperty("dppData");
def _data = inData.split('\n')

_data.each { _rec ->
    def (name, val) = _rec.split('=')

   Cache.set(_rec);
}

for( int i = 0; i < dataContext.getDataCount(); i++ ) {
    InputStream is = dataContext.getStream(i);
    Properties props = dataContext.getProperties(i);

    dataContext.storeStream(is, props);
}
