package com.renxo.cms.domain;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;

/**
 * Base implementation for all localized entities.
 * 
 * @param <LF>
 *            the type of bean used to store localized fields.
 */
public abstract class LocalizedEntityImpl<LF> extends EntityImpl implements
		LocalizedEntity<LF> {

	private static final long serialVersionUID = -7568929225175603344L;

	// ----------------------------------------------------------------------
	// Fields
	// ----------------------------------------------------------------------

	private Map<Locale, LF> localizedFields = new HashMap<Locale, LF>();

	// ----------------------------------------------------------------------
	// Getters and setters
	// ----------------------------------------------------------------------

	/**
	 * Gets the localized fields map.
	 * 
	 * @return the localized fields map.
	 */
	@Override
	public Map<Locale, LF> getLocalizedFields() {
		return localizedFields;
	}

	/**
	 * Sets the localized fields map.
	 * 
	 * @param localizedFields
	 *            the localized fields map.
	 */
	@Override
	public void setLocalizedFields(Map<Locale, LF> localizedFields) {
		this.localizedFields = localizedFields;
	}

	// ----------------------------------------------------------------------
	// Protected methods
	// ----------------------------------------------------------------------

	/**
	 * Gets the localized value for the given property and locale.
	 * 
	 * @param property
	 *            the property name.
	 * @param locale
	 *            a locale.
	 * @return the localized value.
	 */
	protected String localizedProperty(String property, Locale locale) {

		try {
			// Retrieve localized property omitting variant
			LF fields = getLocalizedFields().get(
					new Locale(locale.getLanguage(), locale.getCountry()));

			if (fields != null) {
				Object localizedProperty = PropertyUtils.getProperty(fields,
						property);
				if (localizedProperty != null) {
					return String.valueOf(localizedProperty);
				}
			}

			// Retrieve localized property omitting country and variant
			fields = getLocalizedFields().get(new Locale(locale.getLanguage()));
			if (fields != null) {
				Object localizedProperty = PropertyUtils.getProperty(fields,
						property);
				if (localizedProperty != null) {
					return String.valueOf(localizedProperty);
				}
			}

			// No match, return default property value
			Object value = PropertyUtils.getProperty(this, property);
			return (value != null) ? String.valueOf(value) : null;

		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
