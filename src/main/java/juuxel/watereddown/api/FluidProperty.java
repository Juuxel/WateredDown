/* This file is a part of the Watered Down project
 * by Juuxel, licensed under the MIT license.
 * Full code and license: https://github.com/Juuxel/WateredDown
 */
package juuxel.watereddown.api;

import com.google.common.collect.ImmutableSet;
import net.minecraft.fluid.Fluid;
import net.minecraft.state.property.AbstractProperty;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public final class FluidProperty extends AbstractProperty<FluidProperty.FluidContainer> {
    private final ImmutableSet<FluidContainer> fluids;

    private FluidProperty(String var1, Collection<? extends Fluid> fluids) {
        super(var1, FluidContainer.class);
        this.fluids = ImmutableSet.copyOf(fluids.stream().map(FluidContainer::new).collect(Collectors.toList()));
    }

    @Override
    public Collection<FluidContainer> getValues() {
        return fluids;
    }

    @Override
    public Optional<FluidContainer> getValue(String var1) {
        Identifier id = new Identifier(var1.replace("__", ":"));
        if (Registry.FLUID.contains(id)) {
            return Optional.of(new FluidContainer(Registry.FLUID.get(id)));
        }

        return Optional.empty();
    }

    @Override
    public String getValueAsString(FluidContainer var1) {
        return Registry.FLUID.getId(var1.fluid).toString().replace(":", "__");
    }

    public static FluidProperty create(String var1, Collection<? extends Fluid> fluids) {
        return new FluidProperty(var1, fluids);
    }

    public static final class FluidContainer implements Comparable<FluidContainer> {
        private final Fluid fluid;

        public FluidContainer(Fluid fluid) {
            this.fluid = fluid;
        }

        public Fluid getFluid() {
            return fluid;
        }

        @Override
        public int compareTo(FluidContainer o) {
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
            return obj instanceof FluidContainer && Objects.equals(((FluidContainer) obj).fluid, fluid);
        }
    }
}
