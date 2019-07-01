package sef.module9.activity;

import java.util.*;

/**
 * Implementation of a Radar 
 * 
 *
 */
public class RadarImpl implements Radar{

	List<RadarContact> radarContacts;
	
	/**
	 *  Constructs a new Radar 
	 */
	public RadarImpl(){
		radarContacts = new ArrayList<RadarContact>();
	}
	
	
	/* (non-Javadoc)
	 * @see sef.module8.activity.Radar#addContact(sef.module8.activity.RadarContact)
	 */
	public RadarContact addContact(RadarContact contact) {
		for (RadarContact a: radarContacts){
			if(a.getContactID().equals(contact.getContactID())){
				a.setBearing(contact.getBearing());
				a.setDistance(contact.getDistance());
				return a;
			}

		}
		radarContacts.add(contact);
		return  contact;
	}

	/* (non-Javadoc)
	 * @see sef.module8.activity.Radar#getContact(java.lang.String)
	 */
	public RadarContact getContact(String id) {
		for (RadarContact a: radarContacts) {
			if(a.getContactID().equals(id))
				return a;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see sef.module8.activity.Radar#getContactCount()
	 */
	public int getContactCount() {
		
		return radarContacts.size();
	}

	/* (non-Javadoc)
	 * @see sef.module8.activity.Radar#removeContact(java.lang.String)
	 */
	public RadarContact removeContact(String id) {
		for (RadarContact a: radarContacts ) {
			if(a.getContactID().equals(id)) {
				radarContacts.remove(a);
				return a;
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see sef.module8.activity.Radar#returnContacts()
	 */
	public List<RadarContact> returnContacts() {

			if (getContactCount() == 0)
				return null;
			return new ArrayList<RadarContact> (radarContacts);

	}

	/* (non-Javadoc)
	 * @see sef.module8.activity.Radar#returnContacts(java.util.Comparator)
	 */
	public List<RadarContact> returnContacts(Comparator<RadarContact> comparator) {
		Collections.sort(radarContacts, comparator);
		return new ArrayList<RadarContact> (radarContacts);
//		return Collections.sort(arg0, new DistanceComparator());
	}

	
}
