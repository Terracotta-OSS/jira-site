package com.renxo.cms.domain;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Hibernate;

import com.renxo.cms.domain.user.User;
import com.renxo.cms.remoting.LightweightEntityHelper;
import com.renxo.cms.remoting.LockableEntityProxyFactory;
import com.renxo.cms.remoting.RemotingExclude;
import com.renxo.cms.remoting.RemotingExcludeBehavior;
import com.renxo.cms.remoting.RemotingPoolable;
import com.renxo.cms.remoting.RemotingPoolableHelper;
import com.renxo.cms.remoting.Uninitialized;
import com.renxo.cms.remoting.WeakEntityPool;
import com.renxo.cms.remoting.WeakEntityPoolFactory;

/**
 * Base implementation of <code>Entity</code>.
 * 
 */
public abstract class EntityImpl implements Entity, Serializable {

	private static final long serialVersionUID = 5122862383658849221L;

	// ----------------------------------------------------------------------
	// Constants
	// ----------------------------------------------------------------------

	private static final String CGLIB_SUFFIX = "$$EnhancerByCGLIB$$";

	private static final String JAVASSIST_SUFFIX = "_$$_javassist";

	private static final long INSTANTIATION_MATURITY_DELAY = 10 * 1000L;

	// ----------------------------------------------------------------------
	// Fields
	// ----------------------------------------------------------------------

	private transient Long id;

	private transient Long version;

	private transient Date createdOn;

	private transient User createdBy;

	private transient Date lastModifiedOn;

	private transient User lastModifiedBy;

	private transient long instantiationTimestamp = System.currentTimeMillis();

	// ----------------------------------------------------------------------
	// Getters and setters
	// ----------------------------------------------------------------------

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Long getVersion() {
		return version;
	}

	@Override
	public void setVersion(Long version) {
		this.version = version;
	}

	@Override
	public Date getCreatedOn() {
		return createdOn;
	}

	@Override
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	@Override
	@RemotingExclude(behavior = RemotingExcludeBehavior.PROXY)
	public User getCreatedBy() {
		return createdBy;
	}

	@Override
	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	@Override
	public Date getLastModifiedOn() {
		return lastModifiedOn;
	}

	@Override
	public void setLastModifiedOn(Date lastModifiedOn) {
		this.lastModifiedOn = lastModifiedOn;
	}

	@Override
	@RemotingExclude(behavior = RemotingExcludeBehavior.PROXY)
	public User getLastModifiedBy() {
		return lastModifiedBy;
	}

	@Override
	public void setLastModifiedBy(User lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	// ----------------------------------------------------------------------
	// Business methods
	// ----------------------------------------------------------------------

	@Override
	public String identityText() {
		return identityText(Locale.getDefault());
	}

	@Override
	public String identityText(Locale inLocale) {
		String businessIdentityText = businessIdentityText(inLocale);
		if (businessIdentityText != null) {
			return businessIdentityText;
		} else if (getId() != null) {
			NumberFormat fmt = NumberFormat.getInstance(inLocale);
			return fmt.format(getId());
		} else {
			return null;
		}
	}

	/**
	 * Gets the business identity text for the entity in the given locale.
	 * Should be overridden by subclasses. Default implementation returns
	 * <code>null</code>.
	 * 
	 * @param inLocale
	 *            the locale for which to get the business identity text.
	 * @return the business identity text.
	 */
	protected String businessIdentityText(Locale inLocale) {
		return null;
	}

	// ----------------------------------------------------------------------
	// Identity methods
	// ----------------------------------------------------------------------

	/**
	 * Tests for equality. The <code>other</code> parameter must be of the same
	 * class (or a subclass) of this object for the test to return
	 * <code>true</code>, and the entity ids must match.
	 * 
	 * @return <code>true</code> if <code>other</code> is equal to this
	 *         instance, <code>false</code> otherwise.
	 */
	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		} else if (other == null) {
			return false;
		} else if (!(other instanceof Entity)) {
			return false;
		} else if (!className().equals(((Entity) other).className())) {
			return false;
		} else if (getId() == null) {
			return false;
		} else {
			return getId().equals(((Entity) other).getId());
		}
	}

	/**
	 * Returns the hash code for this entity, which is based on the entity id.
	 * If the entity does not have an id, then the default
	 * {@link Object#equals(Object)} implementation is used.
	 * 
	 * @return the hash code.
	 */
	@Override
	public int hashCode() {
		if (getId() == null) {
			return super.hashCode();
		} else {
			return getId().hashCode();
		}
	}

	// ----------------------------------------------------------------------
	// Miscellaneous methods
	// ----------------------------------------------------------------------

	/**
	 * Returns the simple class name, entity id, and business identity text if
	 * available (all between square brackets).
	 * 
	 * @return a string representation of this entity.
	 */
	@Override
	public String toString() {
		String idText = businessIdentityText(Locale.getDefault());
		return "[" + simpleClassName()
				+ (getId() != null ? " #" + getId() : "")
				+ (idText != null ? " | " + idText : "") + "]";
	}

	@Override
	public String className() {

		String className = getClass().getName();
		if (StringUtils.contains(className, CGLIB_SUFFIX)) {
			className = StringUtils.substringBefore(className, CGLIB_SUFFIX);
		} else if (StringUtils.contains(className, JAVASSIST_SUFFIX)) {
			className = StringUtils
					.substringBefore(className, JAVASSIST_SUFFIX);
		}

		return className;
	}

	@Override
	public String simpleClassName() {

		String simpleClassName = getClass().getSimpleName();
		if (StringUtils.contains(simpleClassName, CGLIB_SUFFIX)) {
			simpleClassName = StringUtils.substringBefore(simpleClassName,
					CGLIB_SUFFIX);
		} else if (StringUtils.contains(simpleClassName, JAVASSIST_SUFFIX)) {
			simpleClassName = StringUtils.substringBefore(simpleClassName,
					JAVASSIST_SUFFIX);
		}

		return simpleClassName;
	}

	@Override
	public String managerClassName() {
		String className = className();
		String simpleClassName = simpleClassName();
		String subpackage = StringUtils.substringBetween(className,
				"com.renxo.cms.domain.", "." + simpleClassName);
		return "com.renxo.cms.service." + subpackage + "." + simpleClassName
				+ "Manager";
	}

	@Override
	public long instantiationTimestamp() {
		return this.instantiationTimestamp;
	}

	@Override
	public boolean mature() {

		// Instance is probably just being deserialized
		if (this.instantiationTimestamp <= 0L) {
			return false;
		}

		long now = System.currentTimeMillis();
		return (now - this.instantiationTimestamp) > INSTANTIATION_MATURITY_DELAY;
	}

	// ----------------------------------------------------------------------
	// Serialization
	// ----------------------------------------------------------------------

	/**
	 * This approach ensures that the id is written first to the stream.
	 * 
	 * @see #readObject(ObjectInputStream)
	 */
	private void writeObject(ObjectOutputStream out) throws IOException {
		out.defaultWriteObject();
		out.writeObject(getId());
		out.writeObject(getVersion());
		out.writeObject(getCreatedOn());
		out.writeObject(getCreatedBy());
		out.writeObject(getLastModifiedOn());
		out.writeObject(getLastModifiedBy());
	}

	/**
	 * This approach ensures that the id is read before traversing the graphs
	 * originating from the {@link User} references, therefore correct {link
	 * {@link #hashCode()} calculation can be performed as soon as the object is
	 * first instantiated. This workaround resolves the deficiencies introduced
	 * by Sun bugs 6208166 and 4957674.
	 * 
	 * @see <a
	 *      href="http://bugs.sun.com/view_bug.do?bug_id=6208166">Bug&nbsp;ID&nbsp;6208166</a>
	 * @see <a
	 *      href="http://bugs.sun.com/view_bug.do?bug_id=4957674">Bug&nbsp;ID&nbsp;4957674</a>
	 */
	private void readObject(ObjectInputStream in) throws IOException,
			ClassNotFoundException {

		in.defaultReadObject();
		this.id = (Long) in.readObject();
		this.version = (Long) in.readObject();
		this.createdOn = (Date) in.readObject();
		this.createdBy = (User) in.readObject();
		this.lastModifiedOn = (Date) in.readObject();
		this.lastModifiedBy = (User) in.readObject();
		this.instantiationTimestamp = System.currentTimeMillis();
	}

	/**
	 * Resolves instances of classes annotated with {@link RemotingPoolable}
	 * using a {@link WeakEntityPool} configured globally via
	 * {@link WeakEntityPoolFactory}.
	 * <p>
	 * This method needs to be protected (not private) in order for it to be
	 * accessible by subclasses and dynamic proxies.
	 * 
	 * @return the resolved entity instance.
	 * @throws ObjectStreamException
	 *             if an error occurs.
	 */
	protected Object readResolve() throws ObjectStreamException {

		// Cannot pool without either id or version
		if (getId() == null || getVersion() == null) {
			return this;
		}

		// Uninitialized entities are not pooled
		if (this instanceof Uninitialized || !Hibernate.isInitialized(this)) {
			return this;
		}

		// Cannot pool without a registered WeakEntityPool
		WeakEntityPool pool = WeakEntityPoolFactory.getWeakEntityPool();
		if (pool == null) {
			return this;
		}

		// Only pool entities annotated with RemotingPoolable
		if (!RemotingPoolableHelper.isRemotingPoolable(this)) {
			return this;
		}

		// Do not pool lightweight instances
		if (LightweightEntityHelper.isLightweightEntity(this)) {
			return this;
		}

		try {
			// Pooled entities need to be locked to ensure thread-safety
			Entity proxy = LockableEntityProxyFactory.createProxy(this, true);
			return pool.replace(proxy);
		} catch (Exception e) {
			throw new InvalidObjectException(e.getMessage());
		}
	}
}
