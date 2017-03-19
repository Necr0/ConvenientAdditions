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
			List<Long> list=new ArrayList<>();
			int count=buf.readInt();
			for(int i=0;i<count;i++){
				list.add(buf.readLong());
			}
			return list;
		}

		@Override
		public DataParameter<List<Long>> createKey(int id) {
            return new DataParameter<>(id, this);
		}
	};

	public static final DataSerializer<List<String>> LISTSTRING=new DataSerializer<List<String>>(){
		@Override
		public void write(PacketBuffer buf, List<String> value) {
			buf.writeInt(value.size());
			for(String s:value){
				buf.writeString(s);
			}
		}

		@Override
		public List<String> read(PacketBuffer buf) throws IOException {
			List<String> list=new ArrayList<>();
			int count=buf.readInt();
			for(int i=0;i<count;i++){
				list.add(buf.readString(64));
			}
			return list;
		}

		@Override
		public DataParameter<List<String>> createKey(int id) {
			return new DataParameter<>(id, this);
		}
	};

	static{
		DataSerializers.registerSerializer(LISTLONG);
		DataSerializers.registerSerializer(LISTSTRING);
	}
}
