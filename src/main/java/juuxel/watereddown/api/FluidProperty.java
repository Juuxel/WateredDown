/* This file is a part of the Watered Down project
 * by Juuxel, licensed under the MIT license.
 * Full code and license: https://github.com/Juuxel/WateredDown
 */
package juuxel.watereddown.api;

import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.state.property.AbstractProperty;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public final class FluidProperty extends AbstractProperty<FluidProperty.Wrapper> {
    public static final Wrapper EMPTY = new Wrapper(Fluids.EMPTY);
    public static final Wrapper WATER = new Wrapper(Fluids.WATER);
    public static final Wrapper LAVA = new Wrapper(Fluids.LAVA);
    private static final String COLON_REPLACEMENT = "_wd_";

    private FluidProperty(String var1) {
        super(var1, Wrapper.class);
    }

    @Override
    public Collection<Wrapper> getValues() {
        return Registry.FLUID.stream().map(Wrapper::new).collect(Collectors.toList());
    }

    @Override
    public Optional<Wrapper> getValue(String var1) {
        Identifier id = new Identifier(var1.replace(COLON_REPLACEMENT, ":"));
        if (Registry.FLUID.contains(id)) {
            return Optional.of(new Wrapper(Registry.FLUID.get(id)));
        }

        return Optional.empty();
    }

    @Override
    public String getValueAsString(Wrapper var1) {
        return Registry.FLUID.getId(var1.fluid).toString().replace(":", COLON_REPLACEMENT);
    }

    public static FluidProperty create(String var1) {
        return new FluidProperty(var1);
    }

    public static final class Wrapper implements Comparable<Wrapper> {
        private final Fluid fluid;

        public Wrapper(Fluid fluid) {
            this.fluid = fluid;
        }

        public Fluid getFluid() {
            return fluid;
        }

        @Override
        public int compareTo(Wrapper o) {
            return Integer.compare(getId(fluid), getId(o.fluid));
        }

        private int getId(Fluid fluid) {
            return fluid != null ? Registry.FLUID.getRawId(fluid) : -1;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(fluid);
        }

        @Override
        public boolean equals(Object obj) {
            return obj instanceof Wrapper && Objects.equals(((Wrapper) obj).fluid, fluid);
        }
    }
}
