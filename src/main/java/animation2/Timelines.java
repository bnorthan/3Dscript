package animation2;

import java.util.ArrayList;
import java.util.List;

public class Timelines {

	private final List<CtrlPoints> timelines = new ArrayList<CtrlPoints>();
	private final List<String> names = new ArrayList<String>();

	private final int nChannels;

	public Timelines(int nChannels, int tmin, int tmax) {
		this.nChannels = nChannels;

		names.add("X Translation");
		names.add("Y Translation");
		names.add("Z Translation");

		names.add("X Rotation");
		names.add("Y Rotation");
		names.add("Z Rotation");

		names.add("Scale");

		names.add("Bounding Box X");
		names.add("Bounding Box Y");
		names.add("Bounding Box Z");
		names.add("Bounding Box W");
		names.add("Bounding Box H");
		names.add("Bounding Box D");

		names.add("Near");
		names.add("Far");

		for(int c = 0; c < nChannels; c++) {
			names.add("Channel " + (c + 1) + " color min");
			names.add("Channel " + (c + 1) + " color max");
			names.add("Channel " + (c + 1) + " color gamma");
			names.add("Channel " + (c + 1) + " alpha min");
			names.add("Channel " + (c + 1) + " alpha max");
			names.add("Channel " + (c + 1) + " alpha gamma");
		}

//		timelines.add(new CtrlPoints(new LinePoint(tmin, kf.dx), new LinePoint(tmax, kf.dx)));
//		timelines.add(new CtrlPoints(new LinePoint(tmin, kf.dy), new LinePoint(tmax, kf.dy)));
//		timelines.add(new CtrlPoints(new LinePoint(tmin, kf.dz), new LinePoint(tmax, kf.dz)));
//
//		timelines.add(new CtrlPoints(new LinePoint(tmin, kf.angleX), new LinePoint(tmax, kf.angleX)));
//		timelines.add(new CtrlPoints(new LinePoint(tmin, kf.angleY), new LinePoint(tmax, kf.angleY)));
//		timelines.add(new CtrlPoints(new LinePoint(tmin, kf.angleZ), new LinePoint(tmax, kf.angleZ)));
//
//		timelines.add(new CtrlPoints(new LinePoint(tmin, kf.scale), new LinePoint(tmax, kf.scale)));
//
//		timelines.add(new CtrlPoints(new LinePoint(tmin, kf.bbx), new LinePoint(tmax, kf.bbx)));
//		timelines.add(new CtrlPoints(new LinePoint(tmin, kf.bby), new LinePoint(tmax, kf.bby)));
//		timelines.add(new CtrlPoints(new LinePoint(tmin, kf.bbz), new LinePoint(tmax, kf.bbz)));
//		timelines.add(new CtrlPoints(new LinePoint(tmin, kf.bbw), new LinePoint(tmax, kf.bbw)));
//		timelines.add(new CtrlPoints(new LinePoint(tmin, kf.bbh), new LinePoint(tmax, kf.bbh)));
//		timelines.add(new CtrlPoints(new LinePoint(tmin, kf.bbd), new LinePoint(tmax, kf.bbd)));
//
//		timelines.add(new CtrlPoints(new LinePoint(tmin, kf.near), new LinePoint(tmax, kf.near)));
//		timelines.add(new CtrlPoints(new LinePoint(tmin, kf.far),  new LinePoint(tmax, kf.far)));
//
//		for(int c = 0; c < nChannels; c++) {
//			RenderingSettings rs = kf.renderingSettings[c];
//			timelines.add(new CtrlPoints(new LinePoint(tmin, rs.colorMin),  new LinePoint(tmax, rs.colorMin)));
//			timelines.add(new CtrlPoints(new LinePoint(tmin, rs.colorMax),  new LinePoint(tmax, rs.colorMax)));
//			timelines.add(new CtrlPoints(new LinePoint(tmin, rs.colorGamma),  new LinePoint(tmax, rs.colorGamma)));
//			timelines.add(new CtrlPoints(new LinePoint(tmin, rs.alphaMin),  new LinePoint(tmax, rs.alphaMin)));
//			timelines.add(new CtrlPoints(new LinePoint(tmin, rs.alphaMax),  new LinePoint(tmax, rs.alphaMax)));
//			timelines.add(new CtrlPoints(new LinePoint(tmin, rs.alphaGamma),  new LinePoint(tmax, rs.alphaGamma)));
//		}

		timelines.add(new CtrlPoints());
		timelines.add(new CtrlPoints());
		timelines.add(new CtrlPoints());

		timelines.add(new CtrlPoints());
		timelines.add(new CtrlPoints());
		timelines.add(new CtrlPoints());

		timelines.add(new CtrlPoints());

		timelines.add(new CtrlPoints());
		timelines.add(new CtrlPoints());
		timelines.add(new CtrlPoints());
		timelines.add(new CtrlPoints());
		timelines.add(new CtrlPoints());
		timelines.add(new CtrlPoints());

		timelines.add(new CtrlPoints());
		timelines.add(new CtrlPoints());

		for(int c = 0; c < nChannels; c++) {
//			RenderingSettings rs = kf.renderingSettings[c];
			timelines.add(new CtrlPoints());
			timelines.add(new CtrlPoints());
			timelines.add(new CtrlPoints());
			timelines.add(new CtrlPoints());
			timelines.add(new CtrlPoints());
			timelines.add(new CtrlPoints());
		}
	}

	public boolean isEmpty() {
		for(CtrlPoints ctrls : timelines)
			if(ctrls.size() > 0)
				return false;
		return true;
	}

	private void record(int i, int t, double v) {
		if(v != Keyframe.UNSET)
			timelines.get(i).add(t, v);
		else {
			LinePoint lp = timelines.get(i).getPointAt(t);
			timelines.get(i).remove(lp);
		}
	}

	public void recordFrame(Keyframe kf) {
		int i = 0;
		int t = kf.getFrame();

		record(i++, t, kf.dx);
		record(i++, t, kf.dy);
		record(i++, t, kf.dz);

		record(i++, t, kf.angleX);
		record(i++, t, kf.angleY);
		record(i++, t, kf.angleZ);

		record(i++, t, kf.scale);

		record(i++, t, kf.bbx);
		record(i++, t, kf.bby);
		record(i++, t, kf.bbz);
		record(i++, t, kf.bbw);
		record(i++, t, kf.bbh);
		record(i++, t, kf.bbd);

		record(i++, t, kf.near);
		record(i++, t, kf.far);

		for(int c = 0; c < nChannels; c++) {
			RenderingSettings rs = kf.renderingSettings[c];
			record(i++, t, rs.colorMin);
			record(i++, t, rs.colorMax);
			record(i++, t, rs.colorGamma);
			record(i++, t, rs.alphaMin);
			record(i++, t, rs.alphaMax);
			record(i++, t, rs.alphaGamma);
		}
	}

	public Keyframe getInterpolatedFrame(int t) {
		Keyframe kf = new Keyframe(t);
		int i = 0;

		kf.dx = (float)timelines.get(i++).getInterpolatedValue(t);
		kf.dy = (float)timelines.get(i++).getInterpolatedValue(t);
		kf.dz = (float)timelines.get(i++).getInterpolatedValue(t);

		kf.angleX = timelines.get(i++).getInterpolatedValue(t);
		kf.angleY = timelines.get(i++).getInterpolatedValue(t);
		kf.angleZ = timelines.get(i++).getInterpolatedValue(t);

		kf.scale = (float)timelines.get(i++).getInterpolatedValue(t);

		kf.bbx = (int)Math.round(timelines.get(i++).getInterpolatedValue(t));
		kf.bby = (int)Math.round(timelines.get(i++).getInterpolatedValue(t));
		kf.bbz = (int)Math.round(timelines.get(i++).getInterpolatedValue(t));
		kf.bbw = (int)Math.round(timelines.get(i++).getInterpolatedValue(t));
		kf.bbh = (int)Math.round(timelines.get(i++).getInterpolatedValue(t));
		kf.bbd = (int)Math.round(timelines.get(i++).getInterpolatedValue(t));

		kf.near = (float)timelines.get(i++).getInterpolatedValue(t);
		kf.far  = (float)timelines.get(i++).getInterpolatedValue(t);

		kf.renderingSettings = new RenderingSettings[nChannels];
		for(int c = 0; c < nChannels; c++) {
			kf.renderingSettings[c] = new RenderingSettings(
					(float)timelines.get(i + 3).getInterpolatedValue(t), // alphamin
					(float)timelines.get(i + 4).getInterpolatedValue(t), // alphamax
					(float)timelines.get(i + 5).getInterpolatedValue(t), // alphagamma
					(float)timelines.get(i + 0).getInterpolatedValue(t), // colormin
					(float)timelines.get(i + 1).getInterpolatedValue(t), // colormax
					(float)timelines.get(i + 2).getInterpolatedValue(t)  // colorgamma
			);
			i += 6;
		}
		return kf;
	}

	public String getName(int i) {
		return names.get(i);
	}

	public CtrlPoints get(int i) {
		return timelines.get(i);
	}

	public int size() {
		return names.size();
	}

	public String[] getNames() {
		String[] names = new String[size()];
		this.names.toArray(names);
		return names;
	}
}
