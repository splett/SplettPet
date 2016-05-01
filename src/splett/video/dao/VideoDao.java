package splett.video.dao;

import java.util.List;

import splett.dao.Dao;
import splett.video.Video;

public interface VideoDao extends Dao<Video>{

	public List<Video> listAllVideos(int id);
	public List<Video> listVideosPublicos(int id);
}
