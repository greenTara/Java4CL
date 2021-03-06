package functional;

import static fj.Function.curry;

import java.util.Iterator;

import fj.Ord;
import fj.data.List;
import fj.data.Set;
import fj.F;

public class EqSet<A> implements Iterable<A> {
	
	private EqSet(final Set<A> set){
		this.set = set;
	}
	private Set<A> set;
	//static factory method
	@SafeVarargs
	public static <B> EqSet<B> eqSet(final B... as){
		return new EqSet<B>(Set.set(Ord.hashEqualsOrd(), as));
	}
	
	public static <B> EqSet<B> eqSet(final Set<B> set){
		return new EqSet<B>(Set.iterableSet(Ord.hashEqualsOrd(), set));
	}
	
	private Set<A> set(){
		return set;
	}

	private static <B> Set<B> set(final EqSet<B> x){
		return x.set();
	}

	private static <B> F<EqSet<B>, Set<B>> set_() {
		return s -> set(s);
	}
	
	public boolean contains(final Object m) {
		//TODO Using this naive implementation based on the iterator
		// makes the equals method quadratic in the size of the set
		if (m == null) return false;
		for (A x : set){
			if( x == m ) return true;
			if( (x != null) && (x.equals(m))) return true;
		}
		return false;
		//try{
		//	@SuppressWarnings("unchecked")
		//	final A a = (A) m;
		//	return set.member(a);
		//}
		//catch(ClassCastException unused){
		//	return false;		
        //} catch (NullPointerException unused) {
         //  return false;
        //}
	}

	public boolean containsAll(final Iterable<?> c) {
		for (Object m : c){
			if(!contains(m)) return false;
		}
		return true;
	}
	
	public int size() {
		return set.size();
	}

	public boolean isEmpty() {
		return set.isEmpty();
	}
	
	public EqSet<A> insert(final A m) {
		return eqSet(set.insert(m));
	}

	public EqSet<A> delete(final A a) {
		return eqSet(set.delete(a));
	}
	public static <B> EqSet<B> fromList(final List<B> c) {
		return c.foldLeft( x -> (s -> x.insert(s)), EqSet.eqSet());			
	}
	
	public EqSet<A> filter(final F<A, Boolean> H){
		EqSet<A> s = EqSet.eqSet();
		for (A c : set){
			if (H.f(c)) s = s.insert(c);
		}
		return s;
	}

	@Override
	public Iterator<A> iterator() {
		return set.iterator();
	}

	//Monad methods
	// static unit method
	public static <B> EqSet<B> unit(final B b){
		return eqSet(b);
	}
	
	//first-class version of unit
	public static <B> F<B, EqSet<B>> unit_(){
		return b -> unit(b);
	}
	
	// join method
    //assertEquals( EqSet.join(EqSet.unit(EqSet.unit(x))), EqSet.unit(x) );
    public static <B> EqSet<B> join(final EqSet<EqSet<B>> x){
		final Ord<B> ordb = Ord.hashEqualsOrd();
		final Ord<Set<B>> ordsetb = Ord.setOrd(ordb);
    	return eqSet(Set.join(ordb, x.set().map(ordsetb, EqSet.set_())));
    }
    
	// first-class version of join(x)
	// F<EqSet<EqSet<B>>, EqSet<B>> joinf = EqSet.join_(); 
    // assertEquals(joinf.f(x), EqSet.join(x));
	public static <B> F<EqSet<EqSet<B>>, EqSet<B>> join_() {
		return s -> join(s);
	}

	// map method
	// A x;
	// G<A, EqSet<A>> F;
    // assertEquals( EqSet.unit(G.f(x)) , EqSet.unit(x).map(G) );
	// Test: must preserve composition:
	// F<A, B> G;
	// F<B, C> H
    // assertEquals( w.map(F).map(G), w.map(Function.compose(G, F) )   );
	// Test: must preserve identity:   
	// EqSet<A> x;
    // assertEquals( x.map( s -> s ), x );	
	public <B> EqSet<B> map( final F<A, B> f) {
		final Ord<B> ordb = Ord.hashEqualsOrd();
		return eqSet(set.map(ordb, f));
	}

	//static version of map
	public static <B, C> EqSet<C> map(final F<B, C> f, final EqSet<B> x) {
		return x.map(f);
	}

	// first-class version of map
	public static <B, C> F<F<B, C>, F<EqSet<B>, EqSet<C>>> map_() {
		return curry((f, as) -> as.map(f));
	}

	// bind method
	// A x;
	// F<A, EqSet<A>> G;
    // assertEquals(  EqSet.unit(x).bind(G) , G.f(x) );
	// EqSet<A> z;
    // assertEquals( z.bind(s -> EqSet.unit(G.f(s))) , z.map(G));
	public <B> EqSet<B> bind(final F<A, EqSet<B>> f) {
		return join(map(f));
	}

	//static version of bind
	public static <B, C> EqSet<C> bind(final F<B, EqSet<C>> f, final EqSet<B> x) {
		return x.bind(f);
	}

	// first-class version of bind
	public static <B, C> F<F<B, EqSet<C>>, F<EqSet<B>, EqSet<C>>> bind_() {
		return curry((f, as) -> as.bind(f));
	}

	@Override
    public int hashCode() {
        int h = 0;
        Iterator<A> i = iterator();
        while (i.hasNext()) {
            A obj = i.next();
            if (obj != null)
                h += obj.hashCode();
        }
        return h + size();
    }

	@Override
	public boolean equals(final Object o) {
        if (o == this)
            return true;

        if (!(o instanceof EqSet))
            return false;
        EqSet<?> c = (EqSet<?>) o;
        if (c.size() != size())
            return false;
        try {
            return containsAll(c);
        } catch (ClassCastException unused)   {
            return false;
        } catch (NullPointerException unused) {
            return false;
        }
	}

	public EqSet<A> union(EqSet<A> x) {
		return eqSet(set.union(x.set));
	}

	public static <B> EqSet<B> empty() {
		return EqSet.eqSet();
	}

}
