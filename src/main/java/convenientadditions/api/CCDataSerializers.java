package convenientadditions.api;

import net.minecraft.network.PacketBuffer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.DataSerializers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CCDataSerializers {
	public static final DataSerializer<List<Long>> LISTLONG=new DataSerializer<List<Long>>(){
		@Override
		public void write(PacketBuffer buf, List<Long> value) {
			buf.writeInt(value.size());
			for(Long l:value){
				buf.writeLong(l);
			}
		}

		@Override
		public List<Long> read(PacketBuffer buf) throws IOException {
			List<Long> list=new ArrayList<Long>();
			int count=buf.readInt();
			for(int i=0;i<count;i++){
				list.add(buf.readLong());
			}
			return list;
		}

		@Override
		public DataParameter<List<Long>> createKey(int id) {
            return new DataParameter<List<Long>>(id, this);
		}
	};
	
	static{
		DataSerializers.registerSerializer(LISTLONG);
	}
}
